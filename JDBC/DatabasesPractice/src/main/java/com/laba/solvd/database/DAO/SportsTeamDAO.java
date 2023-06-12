package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SportsTeamDAO {

	public void createSportsTeam(SportsTeam sportsTeam) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam(sportName, gamesPlayed) VALUES(?, ?)");
			ps.setString(1, sportsTeam.getSportName());
			ps.setInt(2, sportsTeam.getGamesPlayed());
			
			ps.executeUpdate();
			
			System.out.println("Sports Team Created in Database");
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<SportsTeam> getAllSportsTeams() {
		List<SportsTeam> sportsTeams = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM sportsteam");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				SportsTeam sportsTeam = new SportsTeam();
				sportsTeam.setSportName(resultSet.getString("sportName"));
				sportsTeam.setGamesPlayed(resultSet.getInt("gamesPlayed"));
				
				sportsTeams.add(sportsTeam);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return sportsTeams;
	}
	
	public SportsTeam getSportsTeamById(int Id) {
		SportsTeam sportsTeam = new SportsTeam();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement psSportsTeamFields = connection.prepareStatement("SELECT * FROM sportsteam WHERE id=?");
			psSportsTeamFields.setInt(1, Id);
			ResultSet SportsTeamFields = psSportsTeamFields.executeQuery();
			
			sportsTeam.setSportName(SportsTeamFields.getString("sportName"));
			sportsTeam.setGamesPlayed(SportsTeamFields.getInt("gamesPlayed"));
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return sportsTeam;
	}
	
	public void deleteSportsTeam(SportsTeam sportsTeam) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement DeleteSportsTeam = connection.prepareStatement("DELETE FROM sportsteam WHERE id=?");
			DeleteSportsTeam.setInt(1, sportsTeam.getId());
			DeleteSportsTeam.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSportsTeam(SportsTeam sportsTeam) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement UpdateSportsTeam = connection.prepareStatement("UPDATE sportsteam set sportName=?, gamesPlayed=? WHERE id=?");
			UpdateSportsTeam.setString(1, sportsTeam.getSportName());
			UpdateSportsTeam.setInt(2, sportsTeam.getGamesPlayed());
			UpdateSportsTeam.setInt(3, sportsTeam.getId());
			UpdateSportsTeam.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getSportsTeamStudents(SportsTeam sportsTeam) {
		List<Student> students = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM sportsteam_has_student where SportsTeam_id=?");
			ps.setInt(1, sportsTeam.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().getStudentById(ids.getInt("Student_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToSportsTeam(SportsTeam sportsTeam, Student student) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam_has_student(SportsTeam_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, sportsTeam.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
