package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;

public class SportsTeamDAO extends AbstractDAO<SportsTeam> implements ICreatableNoRelationship<SportsTeam>{

	public SportsTeamDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(SportsTeam sportsTeam) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam(sportName, gamesPlayed) VALUES(?, ?)");) {
			ps.setString(1, sportsTeam.getSportName());
			ps.setInt(2, sportsTeam.getGamesPlayed());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<SportsTeam> selectAll() {
		List<SportsTeam> sportsTeams = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "sportsteam"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				SportsTeam sportsTeam = new SportsTeam();
				sportsTeam.setSportName(resultSet.getString("sportName"));
				sportsTeam.setGamesPlayed(resultSet.getInt("gamesPlayed"));
				sportsTeams.add(sportsTeam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsTeams;
	}
	
	@Override
	public SportsTeam selectById(int Id) {
		SportsTeam sportsTeam = new SportsTeam();
		try (PreparedStatement psSportsTeamFields = connection.prepareStatement(String.format(GET_BY_ID, "sportsteam"));) {
			psSportsTeamFields.setInt(1, Id);
			ResultSet SportsTeamFields = psSportsTeamFields.executeQuery();
			if (SportsTeamFields.next()) {
				sportsTeam.setSportName(SportsTeamFields.getString("sportName"));
				sportsTeam.setGamesPlayed(SportsTeamFields.getInt("gamesPlayed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsTeam;
	}
	
	@Override
	public void delete(SportsTeam sportsTeam) {
		try (PreparedStatement DeleteSportsTeam = connection.prepareStatement("DELETE FROM sportsteam WHERE id=?");) {
			DeleteSportsTeam.setInt(1, sportsTeam.getId());
			DeleteSportsTeam.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(SportsTeam sportsTeam) {
		try (PreparedStatement UpdateSportsTeam = connection.prepareStatement("UPDATE sportsteam set sportName=?, gamesPlayed=? WHERE id=?");) {
			UpdateSportsTeam.setString(1, sportsTeam.getSportName());
			UpdateSportsTeam.setInt(2, sportsTeam.getGamesPlayed());
			UpdateSportsTeam.setInt(3, sportsTeam.getId());
			UpdateSportsTeam.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getSportsTeamStudents(SportsTeam sportsTeam) {
		List<Student> students = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM sportsteam_has_student where SportsTeam_id=?");) {
			ps.setInt(1, sportsTeam.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().selectById(ids.getInt("Student_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToSportsTeam(SportsTeam sportsTeam, Student student) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam_has_student(SportsTeam_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, sportsTeam.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}