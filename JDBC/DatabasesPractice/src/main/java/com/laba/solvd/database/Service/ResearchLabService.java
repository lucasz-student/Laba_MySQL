package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.ResearchLabDAO;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;

public class ResearchLabService {
	
	private ResearchLabDAO ResearchLabDAO;
	
	public ResearchLabService(ResearchLabDAO ResearchLabDAO) {	
		this.ResearchLabDAO=ResearchLabDAO;
	}

	public void enrollNewResearchLab(ResearchLab ResearchLab, Professor professor, Student student) {
		this.ResearchLabDAO.create(ResearchLab);
		this.ResearchLabDAO.addProfessorToResearchLab(ResearchLab, professor);
		this.ResearchLabDAO.addStudentToResearchLab(ResearchLab, student);
	}
	
	public void createResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.create(ResearchLab);
	}
	
	public ResearchLab getResearchLabByID(int Id) {
		return this.ResearchLabDAO.selectById(Id);
	}
	
	public List<ResearchLab> getAllClasses() {
		return this.ResearchLabDAO.selectAll();
	}
	
	public void updateResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.update(ResearchLab);
	}
	
	public void deleteResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.delete(ResearchLab);
	}
}