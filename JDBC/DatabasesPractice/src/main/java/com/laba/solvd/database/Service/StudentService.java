package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.Gym;
import com.laba.solvd.database.DAO.Professor;
import com.laba.solvd.database.DAO.ResearchLab;
import com.laba.solvd.database.DAO.SportsTeam;
import com.laba.solvd.database.DAO.Student;
import com.laba.solvd.database.DAO.StudentDAO;
import com.laba.solvd.database.DAO.UniClass;

public class StudentService {
	
	private StudentDAO studentDAO;
	
	public StudentService(StudentDAO d) {
		this.studentDAO=d;
	}

	public void createStudent(Student student, Gym gym) {
		this.studentDAO.createStudent(student, gym);
	}
	
	public void deleteStudent(Student student) {
		this.studentDAO.deleteStudent(student);
	}
	
	public void updateStudent(Student student) {
		this.studentDAO.updateStudent(student);
	}
	
	public List<Student> getAllStudents() {
		return this.studentDAO.getAllStudents();
	}
	
	public void addProfessorToStudent(Student student, Professor professor) {
		this.studentDAO.addProfessorToStudent(student, professor);
	}
	
	public void addResearchLabToStudent(ResearchLab researchLab, Student student) {
	this.studentDAO.addResearchLabToStudent(student, researchLab);
	}
	
	public void addSportsTeamToStudent(SportsTeam sportsTeam, Student student) {
		this.studentDAO.addSportsTeamToStudent(student, sportsTeam);
	}
	
	public void addClassToStudent(UniClass C, Student student) {
		this.studentDAO.addClassToStudent(student, C);
	}
}
