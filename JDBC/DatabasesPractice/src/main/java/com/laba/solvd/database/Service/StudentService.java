package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.StudentDAO;
import com.laba.solvd.database.Mapper.StudentMapper;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.UniClass;

public class StudentService extends AbstractService {
	
	private StudentDAO StudentDAO;
	private StudentMapper studentMapper;
	
	public StudentService(StudentDAO d) throws IOException {
		super();
		this.StudentDAO=d;
		this.studentMapper=this.getSession().getMapper(StudentMapper.class);
	}
	
	public void enrollNewStudent(Student student, Gym gym, Professor professor, ResearchLab researchLab, UniClass uniClass, SportsTeam sportsTeam) {
		studentMapper.create(student, gym);
		this.StudentDAO.addProfessorToStudent(student, professor);
		this.StudentDAO.addClassToStudent(student, uniClass);
		this.StudentDAO.addResearchLabToStudent(student, researchLab);
		this.StudentDAO.addSportsTeamToStudent(student, sportsTeam);
	}

	public void createStudent(Student student, Gym gym) {
		studentMapper.create(student, gym);
	}
	
	public void deleteStudent(Student student) {
		studentMapper.delete(student);
	}
	
	public void updateStudent(Student student) {
		studentMapper.update(student);
	}
	
	public List<Student> getAllStudents() {
		return studentMapper.selectAll();
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