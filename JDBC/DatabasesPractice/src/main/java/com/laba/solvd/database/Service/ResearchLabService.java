package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.ResearchLab;
import com.laba.solvd.database.DAO.ResearchLabDAO;

public class ResearchLabService {
	
	private ResearchLabDAO ResearchLabDAO;
	
	public ResearchLabService(ResearchLabDAO ResearchLabDAO) {	
		this.ResearchLabDAO=ResearchLabDAO;
	}
	
	public void createResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.createResearchLab(ResearchLab);
	}
	
	public ResearchLab getResearchLabByID(int Id) {
		return this.ResearchLabDAO.getResearchLabById(Id);
	}
	
	public List<ResearchLab> getAllClasses() {
		return this.ResearchLabDAO.getAllResearchLabs();
	}
	
	public void updateResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.updateResearchLab(ResearchLab);
	}
	
	public void deleteResearchLab(ResearchLab ResearchLab) {
		this.ResearchLabDAO.deleteResearchLab(ResearchLab);
	}
}
