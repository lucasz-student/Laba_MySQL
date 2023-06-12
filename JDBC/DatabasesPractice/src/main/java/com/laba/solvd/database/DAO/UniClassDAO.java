package com.laba.solvd.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UniClassDAO {

	public void createUniClass(UniClass uniClass) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO class(name, subject) VALUES(?, ?)");
			ps.setString(1, uniClass.getName());
			ps.setString(2, uniClass.getSubject());
			
			ps.executeUpdate();
			System.out.println("Class Created in Database");
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public List<UniClass> getAllUniClasses() {
		List<UniClass> uniClasses = new ArrayList<>();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM class");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				UniClass uniClass = new UniClass();
				uniClass.setId(resultSet.getInt("id"));
				uniClass.setName(resultSet.getString("name"));
				uniClass.setSubject(resultSet.getString("subject"));

				uniClasses.add(uniClass);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return uniClasses;
	}
	
	public UniClass getUniClassById(int Id) {
		UniClass uniClass = new UniClass();
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement psUniClassFields = connection.prepareStatement("SELECT * FROM class WHERE id=?");
			psUniClassFields.setInt(1, Id);
			ResultSet UniClassFields = psUniClassFields.executeQuery();
			
			uniClass.setId(UniClassFields.getInt("id"));
			uniClass.setName(UniClassFields.getString("name"));
			uniClass.setSubject(UniClassFields.getString("subject"));
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return uniClass;
	}
	
	public void deleteUniClass(UniClass uniClass) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement DeleteUniClass = connection.prepareStatement("DELETE FROM class WHERE id=?");
			DeleteUniClass.setInt(1, uniClass.getId());
			DeleteUniClass.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(UniClass uniClass) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement UpdateUniClass = connection.prepareStatement("UPDATE class set name=?, set subject=? WHERE id=?");
			UpdateUniClass.setString(1, uniClass.getName());
			UpdateUniClass.setString(2, uniClass.getSubject());

			UpdateUniClass.executeUpdate();
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void addTextBookToClass(UniClass C, TextBook textBook) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Class_id=? WHERE id=?");
			ps.setInt(1, C.getId());
			ps.setInt(2, textBook.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void addProfessorToClass(UniClass C, Professor professor) {
		try (Connection connection = (Connection) ConnectionManager.getSQLConnection().get();) {
			PreparedStatement ps = connection.prepareStatement("UPDATE professor set Class_id=? WHERE id=?");
			ps.setInt(1, C.getId());
			ps.setInt(2, professor.getId());
			ps.executeUpdate();
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public void addStudentToClass(UniClass C, Student student) {
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

