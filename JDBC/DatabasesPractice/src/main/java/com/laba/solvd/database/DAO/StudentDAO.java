package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class StudentDAO extends AbstractDAO<Student> implements ICreatableWithRelationship<Student, Gym>{

	public StudentDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(Student student, Gym gym) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Student(name, yearJoined, major, Gym_id) VALUES(?, ?, ?, ?)");) {
			ps.setString(1, student.getName());
			ps.setInt(2, student.getYearJoined());
			ps.setString(3, student.getMajor());
			ps.setInt(4, gym.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Student> selectAll() {
		List<Student> students = new ArrayList<>();	
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "student"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setMajor(resultSet.getString("major"));
				student.setName(resultSet.getString("name"));
				student.setYearJoined(resultSet.getInt("yearJoined")); 
				student.setGym(new GymDAO().selectById(resultSet.getInt("Gym_id")));
				students.add(student);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	@Override
	public Student selectById(int Id) {
		Student student = new Student();
		try (PreparedStatement psStudentFields = connection.prepareStatement(String.format(GET_BY_ID, "student"));) {
			psStudentFields.setInt(1, Id);
			ResultSet StudentFields = psStudentFields.executeQuery();
			if (StudentFields.next()) {
				student.setId(StudentFields.getInt("id"));
				student.setName(StudentFields.getString("name"));
				student.setMajor(StudentFields.getString("major"));
				student.setYearJoined(StudentFields.getInt("yearJoined"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@Override
	public void delete(Student student) {
		try (PreparedStatement DeleteStudent = connection.prepareStatement("DELETE FROM student WHERE id=?");) {
			DeleteStudent.setInt(1, student.getId());
			DeleteStudent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Student student) {
		try (PreparedStatement UpdateStudent = connection.prepareStatement("UPDATE student set name=?, yearJoined=?, major=? WHERE id=?");) {
			UpdateStudent.setString(1, student.getName());
			UpdateStudent.setInt(2, student.getYearJoined());
			UpdateStudent.setString(3, student.getMajor());
			UpdateStudent.setInt(4, student.getId());
			UpdateStudent.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfessorToStudent(Student student, Professor professor) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Professor_has_Student(Professor_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, professor.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> getStudentProfessors(Student student) {
		List<Professor> professors = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT Professor_id FROM Professor_has_Student where Student_id=?");) {
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				professors.add(new ProfessorDAO().selectById(ids.getInt("Professor_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	public List<ResearchLab> getStudentResearchLabs(Student student) {
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT ResearchLab_id FROM student_has_researchLab where Student_id=?");) {
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				researchLabs.add(new ResearchLabDAO().selectById(ids.getInt("ResearchLab_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	public void addResearchLabToStudent(Student student, ResearchLab researchLab) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO student_has_researchLab(ResearchLab_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, researchLab.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<SportsTeam> getStudentSportsTeams(Student student) {
		List<SportsTeam> sportsTeams = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT SportsTeam_id FROM sportsteam_has_student where Student_id=?");) {
			ps.setInt(1, student.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				sportsTeams.add(new SportsTeamDAO().selectById(ids.getInt("SportsTeam_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return sportsTeams;
	}
	
	public void addSportsTeamToStudent(Student student, SportsTeam sportsTeam) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sportsteam_has_student(SportsTeam_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, sportsTeam.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addClassToStudent(Student student, UniClass C) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO class_has_student(Class_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, C.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}