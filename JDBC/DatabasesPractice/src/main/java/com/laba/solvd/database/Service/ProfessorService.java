package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.ProfessorDAO;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class ProfessorService {

	private ProfessorDAO ProfessorDAO;
	
	public ProfessorService(ProfessorDAO DAO) {	
		this.ProfessorDAO=DAO;
	}
	
	public void enrollNewProfessor(Professor Professor, UniClass C, Student student, ResearchLab researchLab) {
		this.ProfessorDAO.create(Professor, C);
		this.ProfessorDAO.addResearchLabToProfessor(Professor, researchLab);
		this.ProfessorDAO.addStudentToProfessor(Professor, student);
	}
	
	public void createProfessor(Professor Professor, UniClass C) {
		this.ProfessorDAO.create(Professor, C);
	}
	
	public Professor getProfessorByID(int Id) {
		return this.ProfessorDAO.selectById(Id);
	}
	
	public List<Professor> getAllProfessors() {
		return this.ProfessorDAO.selectAll();
	}
	
	public void updateProfessor(Professor Professor, UniClass C) {
		this.ProfessorDAO.updateWithRelationship(Professor, C);
	}
	
	public void deletProfessor(Professor Professor) {
		this.ProfessorDAO.delete(Professor);
	}
}