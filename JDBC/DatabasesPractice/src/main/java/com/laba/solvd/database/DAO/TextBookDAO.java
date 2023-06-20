package com.laba.solvd.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class TextBookDAO extends AbstractDAO<TextBook> implements ICreatableMultiRelationship<TextBook, Student, UniClass>{
	
	public TextBookDAO() throws InterruptedException, ExecutionException {
		super();
	}

	@Override
	public void create(TextBook textBook, Student student, UniClass C) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO textbook(name, Student_id, bookCondition, Class_id) VALUES(?, ?, ?, ?)");) {
			ps.setString(1, textBook.getName());
			ps.setInt(2, student.getId());
			ps.setString(3, textBook.getBookCondition());
			ps.setInt(4, C.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<TextBook> selectAll() {
		List<TextBook> textBooks = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(GET_ALL, "TextBook"));) {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				TextBook textBook = new TextBook();
				textBook.setId(resultSet.getInt("id"));
				textBook.setName(resultSet.getString("name"));
				textBook.setStudent(new StudentDAO().selectById(resultSet.getInt("Student_id")));
				textBook.setUniClass(new UniClassDAO().selectById(resultSet.getInt("Class_id")));
				textBook.setBookCondition(resultSet.getString("bookCondition"));
				textBooks.add(textBook);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return textBooks;
	}
	
	@Override
	public TextBook selectById(int Id) {
		TextBook textBook = new TextBook();
		try (PreparedStatement psTextBookFields = connection.prepareStatement(String.format(GET_BY_ID, "TextBook"));) {
			psTextBookFields.setInt(1, Id);
			ResultSet TextBookFields = psTextBookFields.executeQuery();
			if (TextBookFields.next()) {
				textBook.setId(TextBookFields.getInt("id"));
				textBook.setName(TextBookFields.getString("name"));
				textBook.setStudent(new StudentDAO().selectById(TextBookFields.getInt("Student_id")));
				textBook.setUniClass(new UniClassDAO().selectById(TextBookFields.getInt("Class_id")));
				textBook.setBookCondition(TextBookFields.getString("bookCondition"));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return textBook;
	}
	
	@Override
	public void delete(TextBook TextBook) {
		try (PreparedStatement DeleteTextBook = connection.prepareStatement("DELETE FROM textbook WHERE id=?");) {
			DeleteTextBook.setInt(1, TextBook.getId());
			DeleteTextBook.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(TextBook textBook) {
		try (PreparedStatement UpdateTextBook = connection.prepareStatement("UPDATE textbook set name=?, Student_id=?, bookCondition=?, Class_id WHERE id=?");) {
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
		try (PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Student_id=? WHERE id=?");) {
			ps.setInt(1, student.getId());
			ps.setInt(2, textBook.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTextBookToClass(TextBook textBook, UniClass C) {	
		try (PreparedStatement ps = connection.prepareStatement("UPDATE textbook set Class_id=? WHERE id=?");) {
			ps.setInt(1, C.getId());
			ps.setInt(2, textBook.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}