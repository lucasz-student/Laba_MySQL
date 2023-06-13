package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.TextBookDAO;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class TextBookService {

	private TextBookDAO TextBookDAO;
	
	public TextBookService(TextBookDAO DAO) {
		this.TextBookDAO = DAO;
	}

	public void createTextBook(TextBook textBook, Student student, UniClass C) {
		this.TextBookDAO.create(textBook, student, C);
	}
	
	public TextBook getTextBookByID(int Id) {
		return this.TextBookDAO.selectById(Id);
	}
	
	public List<TextBook> getAllTextBooks() {
		return this.TextBookDAO.selectAll();
	}
	
	public void updateTextBook(TextBook textbook) {
		this.TextBookDAO.update(textbook);
	}
	
	public void deleteTextBook(TextBook textbook) {
		this.TextBookDAO.delete(textbook);
	}
}