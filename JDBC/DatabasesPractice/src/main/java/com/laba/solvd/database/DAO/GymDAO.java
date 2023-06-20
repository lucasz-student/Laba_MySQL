package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Student;

public class GymDAO extends AbstractDAO<Gym> implements ICreatableNoRelationship<Gym>{

	public GymDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(Gym gym) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO gym(Location, gymAge, price) VALUES(?, ?, ?)");) {
			ps.setString(1, gym.getLocation());
			ps.setInt(2, gym.getGymAge());
			ps.setInt(3, gym.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Gym> selectAll() {
		List<Gym> gyms = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "gym"));) {
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
	
	@Override
	public Gym selectById(int Id) {
		Gym gym = new Gym();
		try (PreparedStatement psGymFields = connection.prepareStatement(String.format(GET_BY_ID, "gym"));) {
			psGymFields.setInt(1, Id);
			ResultSet GymFields = psGymFields.executeQuery();
			if (GymFields.next()) {
				gym.setId(GymFields.getInt("id"));
				gym.setLocation(GymFields.getString("location"));
				gym.setGymAge(GymFields.getInt("gymAge"));
				gym.setPrice(GymFields.getInt("price")); 	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gym;
	}
	
	@Override
	public void delete(Gym gym) {
		try (PreparedStatement DeleteGym = connection.prepareStatement("DELETE FROM gym WHERE id=?");) {
			DeleteGym.setInt(1, gym.getId());
			DeleteGym.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Gym gym) {
		try (PreparedStatement UpdateGym = connection.prepareStatement("UPDATE gym set Location=?, set gymAge=?, set price=? WHERE id=?");) {
			UpdateGym.setString(1, gym.getLocation());
			UpdateGym.setInt(2, gym.getGymAge());
			UpdateGym.setInt(3, gym.getPrice());
			UpdateGym.setInt(4, gym.getId());
			UpdateGym.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudentToGym(Gym gym, Student student) {
		try (PreparedStatement ps = connection.prepareStatement("UPDATE student SET Gym_id=? WHERE id=?");) {
			ps.setInt(1, gym.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException  e) {
			e.printStackTrace();
		} 
	}
}