package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.TextBookDAO;
import com.laba.solvd.database.Mapper.TextBookMapper;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class TextBookService extends AbstractService {

	private TextBookDAO TextBookDAO;
	private TextBookMapper textBookMapper;
	
	public TextBookService(TextBookDAO DAO) throws IOException {
		super();
		this.TextBookDAO = DAO;
		this.textBookMapper=this.getSession().getMapper(TextBookMapper.class);
	}

	public void createTextBook(TextBook textBook, Student student, UniClass C) {
		TextBookDAO.create(textBook, student, C);
	}
	
	public TextBook getTextBookByID(int Id) {
		return textBookMapper.selectById(Id);
	}
	
	public List<TextBook> getAllTextBooks() {
		return textBookMapper.selectAll();
	}
	
	public void updateTextBook(TextBook textbook) {
		textBookMapper.update(textbook);
	}
	
	public void deleteTextBook(TextBook textbook) {
		textBookMapper.delete(textbook);
	}
}