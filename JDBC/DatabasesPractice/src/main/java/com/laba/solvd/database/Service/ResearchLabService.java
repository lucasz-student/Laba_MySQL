package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.ResearchLabDAO;
import com.laba.solvd.database.Mapper.ResearchLabMapper;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;

public class ResearchLabService extends AbstractService{
	
	private ResearchLabDAO ResearchLabDAO;
	private ResearchLabMapper researchLabMapper;
	
	public ResearchLabService(ResearchLabDAO ResearchLabDAO) throws IOException {	
		super();
		this.ResearchLabDAO=ResearchLabDAO;
		this.researchLabMapper = this.getSession().getMapper(ResearchLabMapper.class);
	}

	public void enrollNewResearchLab(ResearchLab ResearchLab, Professor professor, Student student) {
		researchLabMapper.create(ResearchLab);
		this.ResearchLabDAO.addProfessorToResearchLab(ResearchLab, professor);
		this.ResearchLabDAO.addStudentToResearchLab(ResearchLab, student);
	}
	
	public void createResearchLab(ResearchLab ResearchLab) {
		researchLabMapper.create(ResearchLab);
	}
	
	public ResearchLab getResearchLabByID(int Id) {
		return researchLabMapper.selectById(Id);
	}
	
	public List<ResearchLab> getAllClasses() {
		return researchLabMapper.selectAll();
	}
	
	public void updateResearchLab(ResearchLab ResearchLab) {
		researchLabMapper.update(ResearchLab);
	}
	
	public void deleteResearchLab(ResearchLab ResearchLab) {
		researchLabMapper.delete(ResearchLab);
	}
}