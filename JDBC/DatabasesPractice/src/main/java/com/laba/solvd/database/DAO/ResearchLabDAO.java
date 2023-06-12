package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ResearchLabDAO {
	
	public void createResearchLab(ResearchLab researchLab) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab(papersPublished, age, name, topic) VALUES(?, ?, ?, ?)");
			ps.setInt(1, researchLab.getPapersPublished());
			ps.setInt(2, researchLab.getAge());
			ps.setString(3, researchLab.getName());
			ps.setString(4, researchLab.getTopic());
			
			ps.executeUpdate();
			System.out.println("researchLab Created in Database");
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<ResearchLab> getAllResearchLabs() {
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM researchlab");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				ResearchLab researchLab = new ResearchLab();
				researchLab.setPapersPublished(resultSet.getInt("papersPublished"));
				researchLab.setAge(resultSet.getInt("age"));
				researchLab.setName(resultSet.getString("name"));
				researchLab.setTopic(resultSet.getString("topic"));
				researchLabs.add(researchLab);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	public ResearchLab getResearchLabById(int Id) {
		ResearchLab researchLab = new ResearchLab();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement psResearchLabFields = connection.prepareStatement("SELECT * FROM researchlab WHERE id=?");
			psResearchLabFields.setInt(1, Id);
			ResultSet ResearchLabFields = psResearchLabFields.executeQuery();
			
			researchLab.setPapersPublished(ResearchLabFields.getInt("papersPublished"));
			researchLab.setAge(ResearchLabFields.getInt("age"));
			researchLab.setName(ResearchLabFields.getString("name"));
			researchLab.setTopic(ResearchLabFields.getString("topic"));
		
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return researchLab;
	}
	
	public void deleteResearchLab(ResearchLab researchLab) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement DeleteResearchLab = connection.prepareStatement("DELETE FROM researchlab WHERE id=?");
			DeleteResearchLab.setInt(1, researchLab.getId());
			DeleteResearchLab.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void updateResearchLab(ResearchLab researchLab) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement UpdateResearchLab = connection.prepareStatement("UPDATE student set papersPublished=?, set age=?, set name=?, set topic=? WHERE id=?");
			UpdateResearchLab.setInt(1, researchLab.getPapersPublished());
			UpdateResearchLab.setInt(2, researchLab.getAge());
			UpdateResearchLab.setString(3, researchLab.getName());
			UpdateResearchLab.setString(4, researchLab.getTopic());
			UpdateResearchLab.setInt(5, researchLab.getId());
			UpdateResearchLab.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> getResearchLabProfessors(ResearchLab researchLab) {
		List<Professor> professors = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT Professor_id FROM researchlab_has_professor where ResearchLab_id=?");
			ps.setInt(1, researchLab.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				professors.add(new ProfessorDAO().getProfessorById(ids.getInt("Professor_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	public void addProfessorToResearchLab(ResearchLab researchLab, Professor professor) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab_has_professor(Professor_id, ResearchLab_id) VALUES(?, ?)");
			ps.setInt(1, professor.getId());
			ps.setInt(2, researchLab.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getResearchLabStudents(ResearchLab researchLab) {
		List<Student> students = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM student_has_researchLab where ResearchLab_id=?");
			ps.setInt(1, researchLab.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().getStudentById(ids.getInt("Student_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToResearchLab(ResearchLab researchLab, Student student) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO student_has_researchLab(ResearchLab_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, researchLab.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
