package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.ProfessorDAO;
import com.laba.solvd.database.Mapper.ProfessorMapper;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class ProfessorService extends AbstractService{

	private ProfessorDAO ProfessorDAO;
	private ProfessorMapper professorMapper;
	
	public ProfessorService(ProfessorDAO DAO) throws IOException {	
		super();
		this.ProfessorDAO=DAO;
		this.professorMapper=this.getSession().getMapper(ProfessorMapper.class);
	}
	
	public void enrollNewProfessor(Professor Professor, UniClass C, Student student, ResearchLab researchLab) {
		professorMapper.create(Professor, C);
		this.ProfessorDAO.addResearchLabToProfessor(Professor, researchLab);
		this.ProfessorDAO.addStudentToProfessor(Professor, student);
	}
	
	public void createProfessor(Professor Professor, UniClass C) {
		professorMapper.create(Professor, C);
	}
	
	public Professor getProfessorByID(int Id) {
		return professorMapper.selectById(Id);
	}
	
	public List<Professor> getAllProfessors() {
		return professorMapper.selectAll();
	}
	
	public void updateProfessor(Professor Professor, UniClass C) {
		professorMapper.updateWithRelationship(Professor, C);
	}
	
	public void deletProfessor(Professor Professor) {
		professorMapper.delete(Professor);
	}
}