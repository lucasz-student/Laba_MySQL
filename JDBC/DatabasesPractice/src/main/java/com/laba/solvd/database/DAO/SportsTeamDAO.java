package com.laba.solvd.database.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.laba.solvd.database.DAO.Model.SportsTeam;
import com.laba.solvd.database.DAO.Model.Student;

public class SportsTeamDAO {

	public void createSportsTeam(SportsTeam sportsTeam) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam(sportName, gamesPlayed) VALUES(?, ?)");
			ps.setString(1, sportsTeam.getSportName());
			ps.setInt(2, sportsTeam.getGamesPlayed());
			
			ps.executeUpdate();
			
			System.out.println("Sports Team Created in Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<SportsTeam> getAllSportsTeams() {
		Properties props = new Properties();
		List<SportsTeam> sportsTeams = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM sportsteam");
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
	
	public SportsTeam getSportsTeamById(int Id) {
		SportsTeam sportsTeam = new SportsTeam();
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement psSportsTeamFields = connection.prepareStatement("SELECT * FROM sportsteam WHERE id=?");
			psSportsTeamFields.setInt(1, Id);
			ResultSet SportsTeamFields = psSportsTeamFields.executeQuery();
			
			sportsTeam.setSportName(SportsTeamFields.getString("sportName"));
			sportsTeam.setGamesPlayed(SportsTeamFields.getInt("gamesPlayed"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sportsTeam;
	}
	
	public void deleteSportsTeam(SportsTeam sportsTeam) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement DeleteSportsTeam = connection.prepareStatement("DELETE FROM sportsteam WHERE id=?");
			DeleteSportsTeam.setInt(1, sportsTeam.getId());
			DeleteSportsTeam.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSportsTeam(SportsTeam sportsTeam) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement UpdateSportsTeam = connection.prepareStatement("UPDATE sportsteam set sportName=?, gamesPlayed=? WHERE id=?");
			UpdateSportsTeam.setString(1, sportsTeam.getSportName());
			UpdateSportsTeam.setInt(2, sportsTeam.getGamesPlayed());
			UpdateSportsTeam.setInt(3, sportsTeam.getId());
			UpdateSportsTeam.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getSportsTeamStudents(SportsTeam sportsTeam) {
		Properties props = new Properties();
		List<Student> students = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM sportsteam_has_student where SportsTeam_id=?");
			ps.setInt(1, sportsTeam.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().getStudentById(ids.getInt("Student_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToSportsTeam(SportsTeam sportsTeam, Student student) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam_has_student(SportsTeam_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, sportsTeam.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
