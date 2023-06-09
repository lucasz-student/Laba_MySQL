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

import com.laba.solvd.database.DAO.Model.Professor;
import com.laba.solvd.database.DAO.Model.ResearchLab;
import com.laba.solvd.database.DAO.Model.Student;
import com.laba.solvd.database.DAO.Model.UniClass;

public class ProfessorDAO {
	
	public void createProfessor(Professor professor, UniClass uniClass) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO professor(name, age, Class_id) VALUES(?, ?, ?)");
			ps.setString(1, professor.getName());
			ps.setInt(2, professor.getAge());
			ps.setInt(3, professor.getUniClass().getId());
			
			ps.executeUpdate();
			System.out.println("Professor Created in Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> getAllProfessors() {
		Properties props = new Properties();
		List<Professor> professors = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM professor");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Professor professor = new Professor();
				professor.setId(resultSet.getInt("id"));
				professor.setName(resultSet.getString("name"));
				professor.setAge(resultSet.getInt("age"));
				professor.setUniClass(new UniClassDAO().getUniClassById(resultSet.getInt("class_id")));
				professors.add(professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	public Professor getProfessorById(int Id) {
		Professor professor = new Professor();
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement psProfessorFields = connection.prepareStatement("SELECT * FROM professor WHERE id=?");
			psProfessorFields.setInt(1, Id);
			ResultSet StudentFields = psProfessorFields.executeQuery();
			
			professor.setId(StudentFields.getInt("id"));
			professor.setName(StudentFields.getString("name"));
			professor.setAge(StudentFields.getInt("age"));
			professor.setUniClass(new UniClassDAO().getUniClassById(StudentFields.getInt("class_id")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professor;
	}
	
	public void deleteProfessor(Professor professor) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement DeleteProfessor = connection.prepareStatement("DELETE FROM professor WHERE id=?");
			DeleteProfessor.setInt(1, professor.getId());
			DeleteProfessor.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProfessor(Professor professor, UniClass C) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement UpdateProfessor = connection.prepareStatement("UPDATE student set name=?, set age=?, set Class_id=? WHERE id=?");
			UpdateProfessor.setString(1, professor.getName());
			UpdateProfessor.setInt(2, professor.getAge());
			UpdateProfessor.setInt(3, C.getId());
			UpdateProfessor.setInt(4, professor.getId());
			UpdateProfessor.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getProfessorStudents(Professor professor) {
		Properties props = new Properties();
		List<Student> students = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM Professor_has_Student where Professor_id=?");
			ps.setInt(1, professor.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().getStudentById(ids.getInt("Student_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToProfessor(Professor professor, Student student) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Professor_has_Student(Professor_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, professor.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ResearchLab> getProfessorResearchLabs(Professor professor) {
		Properties props = new Properties();
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("SELECT ResearchLab_id FROM researchlab_has_professor where Professor_id=?");
			ps.setInt(1, professor.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				researchLabs.add(new ResearchLabDAO().getResearchLabById(ids.getInt("ResearchLab_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	public void addResearchLabToProfessor(Professor professor, ResearchLab researchLab) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab_has_professor(Professor_id, ResearchLab_id) VALUES(?, ?)");
			ps.setInt(1, professor.getId());
			ps.setInt(2, researchLab.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfessorToClass(UniClass C, Professor professor) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props)) {
			PreparedStatement ps = connection.prepareStatement("UPDATE professor set Class_id=? WHERE id=?");
			ps.setInt(1, C.getId());
			ps.setInt(2, professor.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
