package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;

public class ResearchLabDAO extends AbstractDAO<ResearchLab> implements ICreatableNoRelationship<ResearchLab>{
	
	public ResearchLabDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(ResearchLab researchLab) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab(papersPublished, age, name, topic) VALUES(?, ?, ?, ?)");
			ps.setInt(1, researchLab.getPapersPublished());
			ps.setInt(2, researchLab.getAge());
			ps.setString(3, researchLab.getName());
			ps.setString(4, researchLab.getTopic());
			ps.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ResearchLab> selectAll() {
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "researchlab"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ResearchLab researchLab = new ResearchLab();
				researchLab.setPapersPublished(resultSet.getInt("papersPublished"));
				researchLab.setAge(resultSet.getInt("age"));
				researchLab.setName(resultSet.getString("name"));
				researchLab.setTopic(resultSet.getString("topic"));
				researchLabs.add(researchLab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	@Override
	public ResearchLab selectById(int Id) {
		ResearchLab researchLab = new ResearchLab();
		try (PreparedStatement psResearchLabFields = connection.prepareStatement(String.format(GET_BY_ID, "researchlab"));) {
			psResearchLabFields.setInt(1, Id);
			ResultSet ResearchLabFields = psResearchLabFields.executeQuery();
			researchLab.setPapersPublished(ResearchLabFields.getInt("papersPublished"));
			researchLab.setAge(ResearchLabFields.getInt("age"));
			researchLab.setName(ResearchLabFields.getString("name"));
			researchLab.setTopic(ResearchLabFields.getString("topic"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return researchLab;
	}
	
	@Override
	public void delete(ResearchLab researchLab) {
		try (PreparedStatement DeleteResearchLab = connection.prepareStatement("DELETE FROM researchlab WHERE id=?");) {
			DeleteResearchLab.setInt(1, researchLab.getId());
			DeleteResearchLab.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(ResearchLab researchLab) {
		try (PreparedStatement UpdateResearchLab = connection.prepareStatement("UPDATE student set papersPublished=?, age=?, name=?, topic=? WHERE id=?");) {
			UpdateResearchLab.setInt(1, researchLab.getPapersPublished());
			UpdateResearchLab.setInt(2, researchLab.getAge());
			UpdateResearchLab.setString(3, researchLab.getName());
			UpdateResearchLab.setString(4, researchLab.getTopic());
			UpdateResearchLab.setInt(5, researchLab.getId());
			UpdateResearchLab.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> getResearchLabProfessors(ResearchLab researchLab) {
		List<Professor> professors = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT Professor_id FROM researchlab_has_professor where ResearchLab_id=?");) {
			ps.setInt(1, researchLab.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				professors.add(new ProfessorDAO().selectById(ids.getInt("Professor_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	public void addProfessorToResearchLab(ResearchLab researchLab, Professor professor) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab_has_professor(Professor_id, ResearchLab_id) VALUES(?, ?)");) {
			ps.setInt(1, professor.getId());
			ps.setInt(2, researchLab.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getResearchLabStudents(ResearchLab researchLab) {
		List<Student> students = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM student_has_researchLab where ResearchLab_id=?");) {
			ps.setInt(1, researchLab.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().selectById(ids.getInt("Student_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToResearchLab(ResearchLab researchLab, Student student) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO student_has_researchLab(ResearchLab_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, researchLab.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}