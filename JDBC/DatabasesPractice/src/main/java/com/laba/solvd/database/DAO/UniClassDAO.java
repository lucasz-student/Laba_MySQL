package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class UniClassDAO extends AbstractDAO<UniClass> implements ICreatableNoRelationship<UniClass>{

	public UniClassDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(UniClass uniClass) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO class(name, subject) VALUES(?, ?)");) {
			ps.setString(1, uniClass.getName());
			ps.setString(2, uniClass.getSubject());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UniClass> selectAll() {
		List<UniClass> uniClasses = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "class"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				UniClass uniClass = new UniClass();
				uniClass.setId(resultSet.getInt("id"));
				uniClass.setName(resultSet.getString("name"));
				uniClass.setSubject(resultSet.getString("subject"));
				uniClasses.add(uniClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uniClasses;
	}
	
	@Override
	public UniClass selectById(int Id) {
		UniClass uniClass = new UniClass();
		try (PreparedStatement psUniClassFields = connection.prepareStatement(String.format(GET_BY_ID, "class"));) {
			psUniClassFields.setInt(1, Id);
			ResultSet UniClassFields = psUniClassFields.executeQuery();
			if (UniClassFields.next()) {
				uniClass.setId(UniClassFields.getInt("id"));
				uniClass.setName(UniClassFields.getString("name"));
				uniClass.setSubject(UniClassFields.getString("subject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uniClass;
	}
	
	@Override
	public void delete(UniClass uniClass) {
		try (PreparedStatement DeleteUniClass = connection.prepareStatement("DELETE FROM class WHERE id=?");) {
			DeleteUniClass.setInt(1, uniClass.getId());
			DeleteUniClass.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(UniClass uniClass) {
		try (PreparedStatement UpdateUniClass = connection.prepareStatement("UPDATE class set name=?, subject=? WHERE id=?");) {
			UpdateUniClass.setString(1, uniClass.getName());
			UpdateUniClass.setString(2, uniClass.getSubject());
			UpdateUniClass.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTextBookToClass(UniClass C, TextBook textBook) {
		try (PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Class_id=? WHERE id=?");) {
			ps.setInt(1, C.getId());
			ps.setInt(2, textBook.getId());
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
	
	public void addStudentToClass(UniClass C, Student student) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO class_has_student(Class_id, Student_id) VALUES(?, ?)");) {
			ps.setInt(1, C.getId());
			ps.setInt(2, student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}