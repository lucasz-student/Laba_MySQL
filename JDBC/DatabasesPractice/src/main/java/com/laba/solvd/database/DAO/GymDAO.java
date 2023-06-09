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

import com.laba.solvd.database.DAO.Model.Gym;
import com.laba.solvd.database.DAO.Model.Student;

public class GymDAO {

	public void createGym(Gym gym) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO gym(Location, gymAge, price) VALUES(?, ?, ?)");
			ps.setString(1, gym.getLocation());
			ps.setInt(2, gym.getGymAge());
			ps.setInt(3, gym.getPrice());
			
			ps.executeUpdate();
			System.out.println("Gym Created in Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudentToGym(Gym gym, Student student) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("UPDATE student SET Gym_id=? WHERE id=?");
			ps.setInt(1, gym.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Gym> getAllGyms() {
		Properties props = new Properties();
		List<Gym> gyms = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
		    Class.forName(props.getProperty("db.driver"));
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM gym");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Gym gym = new Gym();
				gym.setId(resultSet.getInt("id"));
				gym.setLocation(resultSet.getString("location"));
				gym.setGymAge(resultSet.getInt("gymAge"));
				gym.setPrice(resultSet.getInt("price")); 
				gyms.add(gym);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gyms;
	}
	
	public Gym getGymById(int Id) {
		Gym gym = new Gym();
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement psGymFields = connection.prepareStatement("SELECT * FROM gym WHERE id=?");
			psGymFields.setInt(1, Id);
			ResultSet GymFields = psGymFields.executeQuery();
			
			gym.setId(GymFields.getInt("id"));
			gym.setLocation(GymFields.getString("location"));
			gym.setGymAge(GymFields.getInt("gymAge"));
			gym.setPrice(GymFields.getInt("price")); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gym;
	}
	
	public void deleteGym(Gym gym) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement DeleteGym = connection.prepareStatement("DELETE FROM gym WHERE id=?");
			DeleteGym.setInt(1, gym.getId());
			DeleteGym.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGym(Gym gym) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement UpdateGym = connection.prepareStatement("UPDATE gym set Location=?, set gymAge=?, set price=? WHERE id=?");
			UpdateGym.setString(1, gym.getLocation());
			UpdateGym.setInt(2, gym.getGymAge());
			UpdateGym.setInt(3, gym.getPrice());
			UpdateGym.setInt(4, gym.getId());
			UpdateGym.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
