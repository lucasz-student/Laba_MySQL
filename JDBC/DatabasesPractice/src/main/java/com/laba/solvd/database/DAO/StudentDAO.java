package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentDAO {

	public void createStudent(Student student, Gym gym) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Student(name, yearJoined, major, Gym_id) VALUES(?, ?, ?, ?)");
			ps.setString(1, student.getName());
			ps.setInt(2, student.getYearJoined());
			ps.setString(3, student.getMajor());
			ps.setInt(4, gym.getId());
			
			ps.executeUpdate();
			System.out.println("Student Created in Database");
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();	
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM student");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setMajor(resultSet.getString("major"));
				student.setName(resultSet.getString("name"));
				student.setYearJoined(resultSet.getInt("yearJoined")); 
				student.setGym(new GymDAO().getGymById(resultSet.getInt("Gym_id")));
				students.add(student);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public Student getStudentById(int Id) {
		Student student = new Student();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement psStudentFields = connection.prepareStatement("SELECT * FROM student WHERE id=?");
			psStudentFields.setInt(1, Id);
			ResultSet StudentFields = psStudentFields.executeQuery();
			
			student.setId(StudentFields.getInt("id"));
			student.setName(StudentFields.getString("name"));
			student.setMajor(StudentFields.getString("major"));
			student.setYearJoined(StudentFields.getInt("yearJoined"));
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public void deleteStudent(Student student) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement DeleteStudent = connection.prepareStatement("DELETE FROM student WHERE id=?");
			DeleteStudent.setInt(1, student.getId());
			DeleteStudent.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(Student student) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement UpdateStudent = connection.prepareStatement("UPDATE student set name=?, set yearJoined=?, set major=? WHERE id=?");
			UpdateStudent.setString(1, student.getName());
			UpdateStudent.setInt(2, student.getYearJoined());
			UpdateStudent.setString(3, student.getMajor());
			UpdateStudent.setInt(4, student.getId());
			UpdateStudent.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfessorToStudent(Student student, Professor professor) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Professor_has_Student(Professor_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, professor.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> getStudentProfessors(Student student) {
		List<Professor> professors = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT Professor_id FROM Professor_has_Student where Student_id=?");
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				professors.add(new ProfessorDAO().getProfessorById(ids.getInt("Professor_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	public List<ResearchLab> getStudentResearchLabs(Student student) {
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT ResearchLab_id FROM student_has_researchLab where Student_id=?");
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				researchLabs.add(new ResearchLabDAO().getResearchLabById(ids.getInt("ResearchLab_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	public void addResearchLabToStudent(Student student, ResearchLab researchLab) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO student_has_researchLab(ResearchLab_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, researchLab.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<SportsTeam> getStudentSportsTeams(Student student) {
		List<SportsTeam> sportsTeams = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT SportsTeam_id FROM sportsteam_has_student where Student_id=?");
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				sportsTeams.add(new SportsTeamDAO().getSportsTeamById(ids.getInt("SportsTeam_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return sportsTeams;
	}
	
	public void addSportsTeamToStudent(Student student, SportsTeam sportsTeam) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam_has_student(SportsTeam_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, sportsTeam.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void addClassToStudent(Student student, UniClass C) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO class_has_student(Class_id, Student_id) VALUES(?, ?)");
			ps.setInt(1, C.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
