package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.UniClassDAO;
import com.laba.solvd.database.Mapper.UniClassMapper;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class UniClassService extends AbstractService {

	private UniClassDAO UniClassDAO;
	private UniClassMapper uniClassMapper;
	
	public UniClassService(UniClassDAO C) throws IOException {	
		super();
		this.UniClassDAO=C;
		this.uniClassMapper=this.getSession().getMapper(UniClassMapper.class);
	}
	
	public void enrollNewClass(UniClass UniClass, Student student, Professor professor, TextBook textBook) {
		uniClassMapper.create(UniClass);
		this.UniClassDAO.addProfessorToClass(UniClass, professor);
		this.UniClassDAO.addStudentToClass(UniClass, student);
		this.UniClassDAO.addTextBookToClass(UniClass, textBook);
	}
	
	public void createClass(UniClass UniClass) {
		uniClassMapper.create(UniClass);
	}
	
	public UniClass getClassByID(int Id) {
		return uniClassMapper.selectById(Id);
	}
	
	public List<UniClass> getAllClasses() {
		return uniClassMapper.selectAll();
	}
	
	public void updateClass(UniClass uniclass) {
		uniClassMapper.update(uniclass);
	}
	
	public void deleteClass(UniClass uniclass) {
		uniClassMapper.delete(uniclass);
	}
}