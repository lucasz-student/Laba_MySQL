package com.laba.solvd.database;

import java.util.List;

import com.laba.solvd.database.DAO.TextBookDAO;
import com.laba.solvd.database.DAO.Model.Student;
import com.laba.solvd.database.DAO.Model.TextBook;
import com.laba.solvd.database.DAO.Model.UniClass;

public class TextBookService {

	private TextBookDAO DAO;
	
	public TextBookService(TextBookDAO DAO) {
		this.DAO = DAO;
	}
	
	public void createTextBook(TextBook textBook, Student student, UniClass C) {
		this.DAO.createTextBook(textBook, student, C);
	}
	
	public TextBook getTextBookByID(int Id) {
		return this.DAO.getTextBookById(Id);
	}
	
	public List<TextBook> getAllTextBooks() {
		return this.DAO.getAllTextBooks();
	}
	
	public void updateTextBook(TextBook textbook) {
		this.DAO.updateStudent(textbook);
	}
	
	public void deleteTextBook(TextBook textbook) {
		this.DAO.deleteTextBook(textbook);
	}
	
	
}
