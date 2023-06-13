package com.laba.solvd.database.Model;

import java.util.ArrayList;
import java.util.List;

public class UniClass {

	private int id;
	private String name;
	private String subject;

	private Professor professor;
	private List<Student> Students = new ArrayList<>();
	private List<TextBook> textBooks = new ArrayList<>();
	
	public UniClass() {
	}
	
	public UniClass(int id, String name, String subject) {
		this.id = id;
		this.name = name;
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void addStudent(Student student) {
		this.Students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.Students.remove(student);
	}
	
	public void addTextBook(TextBook textBook) {
		this.textBooks.add(textBook);
	}
	
	public void removeTextBook(TextBook textBook) {
		this.textBooks.remove(textBook);
	}
}
