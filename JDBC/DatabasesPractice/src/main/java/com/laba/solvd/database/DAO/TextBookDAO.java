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

import com.laba.solvd.database.DAO.Model.Student;
import com.laba.solvd.database.DAO.Model.TextBook;
import com.laba.solvd.database.DAO.Model.UniClass;

public class TextBookDAO {
	
	public void createTextBook(TextBook textBook, Student student, UniClass C) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO textbook(name, Student_id, bookCondition, Class_id) VALUES(?, ?, ?, ?)");
			ps.setString(1, textBook.getName());
			ps.setInt(2, student.getId());
			ps.setString(3, textBook.getBookCondition());
			ps.setInt(4, C.getId());
			
			ps.executeUpdate();
			System.out.println("Textbook Created in Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<TextBook> getAllTextBooks() {
		Properties props = new Properties();
		List<TextBook> textBooks = new ArrayList<>();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM textbook");
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				TextBook textBook = new TextBook();
				textBook.setId(resultSet.getInt("id"));
				textBook.setName(resultSet.getString("name"));
				textBook.setStudent(new StudentDAO().getStudentById(resultSet.getInt("Student_id")));
				// textBook.setUniClass(new UniClassDAO().getUniClassById(resultSet.getInt("Class_id")));
				textBook.setBookCondition(resultSet.getString("bookCondition"));
				textBooks.add(textBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return textBooks;
	}
	
	public TextBook getTextBookById(int Id) {
		TextBook textBook = new TextBook();
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement psTextBookFields = connection.prepareStatement("SELECT * FROM student WHERE id=?");
			psTextBookFields.setInt(1, Id);
			ResultSet TextBookFields = psTextBookFields.executeQuery();
			
			textBook.setId(TextBookFields.getInt("id"));
			textBook.setName(TextBookFields.getString("name"));
			textBook.setStudent(new StudentDAO().getStudentById(TextBookFields.getInt("Student_id")));
			//textBook.setUniClass(new UniClassDAO().getUniClassById(TextBookFields.getInt("Class_id")));
			textBook.setBookCondition(TextBookFields.getString("bookCondition"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return textBook;
	}
	
	public void deleteTextBook(TextBook TextBook) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement DeleteTextBook = connection.prepareStatement("DELETE FROM textbook WHERE id=?");
			DeleteTextBook.setInt(1, TextBook.getId());
			DeleteTextBook.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(TextBook textBook) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement UpdateTextBook = connection.prepareStatement("UPDATE textbook set name=?, set Student_id=?, set bookCondition=?, set Class_id WHERE id=?");
			UpdateTextBook.setString(1, textBook.getName());
			UpdateTextBook.setInt(2, textBook.getStudent().getId());
			UpdateTextBook.setString(3, textBook.getBookCondition());
			UpdateTextBook.setInt(4, textBook.getUniClass().getId());
			UpdateTextBook.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTextBookToStudent(TextBook textBook, Student student) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Student_id=? WHERE id=?");
			ps.setInt(1, student.getId());
			ps.setInt(2, textBook.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTextBookToClass(TextBook textBook, UniClass C) {
		Properties props = new Properties();
		try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try (Connection connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"))) {
			PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Class_id=? WHERE id=?");
			ps.setInt(1, C.getId());
			ps.setInt(2, textBook.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
