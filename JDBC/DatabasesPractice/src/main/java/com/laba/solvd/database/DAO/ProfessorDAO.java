package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class ProfessorDAO extends AbstractDAO<Professor> implements ICreatableWithRelationship<Professor, UniClass> {
	
	public ProfessorDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(Professor professor, UniClass uniClass) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO professor(name, age, Class_id) VALUES(?, ?, ?)");) {
			ps.setString(1, professor.getName());
			ps.setInt(2, professor.getAge());
			ps.setInt(3, professor.getUniClass().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Professor> selectAll() {
		List<Professor> professors = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "professor"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Professor professor = new Professor();
				professor.setId(resultSet.getInt("id"));
				professor.setName(resultSet.getString("name"));
				professor.setAge(resultSet.getInt("age"));
				professor.setUniClass(new UniClassDAO().selectById(resultSet.getInt("class_id")));
				professors.add(professor);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professors;
	}
	
	@Override
	public Professor selectById(int Id) {
		Professor professor = new Professor();
		try (PreparedStatement psProfessorFields = connection.prepareStatement(String.format(GET_BY_ID, "professor"));) {
			psProfessorFields.setInt(1, Id);
			ResultSet StudentFields = psProfessorFields.executeQuery();
			if (StudentFields.next()) {
				professor.setId(StudentFields.getInt("id"));
				professor.setName(StudentFields.getString("name"));
				professor.setAge(StudentFields.getInt("age"));
				professor.setUniClass(new UniClassDAO().selectById(StudentFields.getInt("class_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return professor;
	}
	
	@Override
	public void delete(Professor professor) {
		try (PreparedStatement DeleteProfessor = connection.prepareStatement("DELETE FROM professor WHERE id=?");) {
			DeleteProfessor.setInt(1, professor.getId());
			DeleteProfessor.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Professor professor) {
		try (PreparedStatement UpdateProfessor = connection.prepareStatement("UPDATE student set name=? age=? WHERE id=?");) {
			UpdateProfessor.setString(1, professor.getName());
			UpdateProfessor.setInt(2, professor.getAge());
			UpdateProfessor.setInt(3, professor.getId());
			UpdateProfessor.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateWithRelationship(Professor professor, UniClass C) {
		try (PreparedStatement UpdateProfessor = connection.prepareStatement("UPDATE student set name=?, set age=?, set Class_id=? WHERE id=?");) {
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
		List<Student> students = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT Student_id FROM Professor_has_Student where Professor_id=?");) {
			ps.setInt(1, professor.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				students.add(new StudentDAO().selectById(ids.getInt("Student_id")));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public void addStudentToProfessor(Professor professor, Student student) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Professor_has_Student(Professor_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, professor.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ResearchLab> getProfessorResearchLabs(Professor professor) throws InterruptedException, ExecutionException {
		List<ResearchLab> researchLabs = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT ResearchLab_id FROM researchlab_has_professor where Professor_id=?");) {
			ps.setInt(1, professor.getId());
			ResultSet ids = ps.executeQuery();
			while (ids.next()) {
				researchLabs.add(new ResearchLabDAO().selectById(ids.getInt("ResearchLab_id")));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return researchLabs;
	}
	
	public void addResearchLabToProfessor(Professor professor, ResearchLab researchLab) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO researchlab_has_professor(Professor_id, ResearchLab_id) VALUES(?, ?)");) {
			ps.setInt(1, professor.getId());
			ps.setInt(2, researchLab.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfessorToClass(UniClass C, Professor professor) {
		try (PreparedStatement ps = connection.prepareStatement("UPDATE professor set Class_id=? WHERE id=?");) {
			ps.setInt(1, C.getId());
			ps.setInt(2, professor.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}