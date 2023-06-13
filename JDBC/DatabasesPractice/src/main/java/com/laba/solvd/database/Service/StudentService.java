package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.StudentDAO;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class StudentService {
	
	private StudentDAO StudentDAO;
	
	public StudentService(StudentDAO d) {
		this.StudentDAO=d;
	}
	
	public void enrollNewStudent(Student student, Gym gym, Professor professor, ResearchLab researchLab, UniClass uniClass, SportsTeam sportsTeam) {
		this.StudentDAO.create(student, gym);
		this.StudentDAO.addProfessorToStudent(student, professor);
		this.StudentDAO.addClassToStudent(student, uniClass);
		this.StudentDAO.addResearchLabToStudent(student, researchLab);
		this.StudentDAO.addSportsTeamToStudent(student, sportsTeam);
	}

	public void createStudent(Student student, Gym gym) {
		this.StudentDAO.create(student, gym);
	}
	
	public void deleteStudent(Student student) {
		this.StudentDAO.delete(student);
	}
	
	public void updateStudent(Student student) {
		this.StudentDAO.update(student);
	}
	
	public List<Student> getAllStudents() {
		return this.StudentDAO.selectAll();
	}
	
	public void addProfessorToStudent(Student student, Professor professor) {
		this.StudentDAO.addProfessorToStudent(student, professor);
	}
	
	public void addResearchLabToStudent(ResearchLab researchLab, Student student) {
	this.StudentDAO.addResearchLabToStudent(student, researchLab);
	}
	
	public void addSportsTeamToStudent(SportsTeam sportsTeam, Student student) {
		this.StudentDAO.addSportsTeamToStudent(student, sportsTeam);
	}
	
	public void addClassToStudent(UniClass C, Student student) {
		this.StudentDAO.addClassToStudent(student, C);
	}
}