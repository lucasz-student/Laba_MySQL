package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.Student;
import com.laba.solvd.database.DAO.TextBook;
import com.laba.solvd.database.DAO.TextBookDAO;
import com.laba.solvd.database.DAO.UniClass;

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
