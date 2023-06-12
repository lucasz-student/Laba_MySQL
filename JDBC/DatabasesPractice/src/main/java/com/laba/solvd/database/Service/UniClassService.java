package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.UniClass;
import com.laba.solvd.database.DAO.UniClassDAO;

public class UniClassService {

	private UniClassDAO UniClassDAO;
	
	public UniClassService(UniClassDAO C) {	
		this.UniClassDAO=C;
	}
	
	public void createClass(UniClass UniClass) {
		this.UniClassDAO.createUniClass(UniClass);
	}
	
	public UniClass getClassByID(int Id) {
		return this.UniClassDAO.getUniClassById(Id);
	}
	
	public List<UniClass> getAllClasses() {
		return this.UniClassDAO.getAllUniClasses();
	}
	
	public void updateClass(UniClass uniclass) {
		this.UniClassDAO.updateStudent(uniclass);
	}
	
	public void deleteClass(UniClass uniclass) {
		this.UniClassDAO.deleteUniClass(uniclass);
	}
}