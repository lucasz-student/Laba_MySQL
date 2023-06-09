package com.laba.solvd.database;

import java.util.List;

import com.laba.solvd.database.DAO.ProfessorDAO;
import com.laba.solvd.database.DAO.Model.Professor;
import com.laba.solvd.database.DAO.Model.UniClass;

public class ProfessorService {

	private ProfessorDAO ProfessorDAO;
	
	public ProfessorService(ProfessorDAO ProfessorDAO) {	
		this.ProfessorDAO=ProfessorDAO;
	}
	
	public void createProfessor(Professor Professor, UniClass C) {
		this.ProfessorDAO.createProfessor(Professor, C);
	}
	
	public Professor getProfessorByID(int Id) {
		return this.ProfessorDAO.getProfessorById(Id);
	}
	
	public List<Professor> getAllProfessors() {
		return this.ProfessorDAO.getAllProfessors();
	}
	
	public void updateProfessor(Professor Professor, UniClass C) {
		this.ProfessorDAO.updateProfessor(Professor, C);
	}
	
	public void deletProfessor(Professor Professor) {
		this.ProfessorDAO.deleteProfessor(Professor);
	}
}
