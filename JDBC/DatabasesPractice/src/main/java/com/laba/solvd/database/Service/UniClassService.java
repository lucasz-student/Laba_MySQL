package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.UniClassDAO;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class UniClassService {

	private UniClassDAO UniClassDAO;
	
	public UniClassService(UniClassDAO C) {	
		this.UniClassDAO=C;
	}
	
	public void enrollNewClass(UniClass UniClass, Student student, Professor professor, TextBook textBook) {
		this.UniClassDAO.create(UniClass);
		this.UniClassDAO.addProfessorToClass(UniClass, professor);
		this.UniClassDAO.addStudentToClass(UniClass, student);
		this.UniClassDAO.addTextBookToClass(UniClass, textBook);
	}
	
	public void createClass(UniClass UniClass) {
		this.UniClassDAO.create(UniClass);
	}
	
	public UniClass getClassByID(int Id) {
		return this.UniClassDAO.selectById(Id);
	}
	
	public List<UniClass> getAllClasses() {
		return this.UniClassDAO.selectAll();
	}
	
	public void updateClass(UniClass uniclass) {
		this.UniClassDAO.update(uniclass);
	}
	
	public void deleteClass(UniClass uniclass) {
		this.UniClassDAO.delete(uniclass);
	}
}
