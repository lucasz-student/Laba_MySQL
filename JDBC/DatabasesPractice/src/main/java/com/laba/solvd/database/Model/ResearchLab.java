package com.laba.solvd.database.Model;

import java.util.ArrayList;
import java.util.List;

public class ResearchLab {

	private int id; 
	private int papersPublished; 
	private int age; 
	private String name; 
	private String topic;
	
	private List<Student> Students = new ArrayList<>();
	private List<Professor> Professors = new ArrayList<>();
	
	public ResearchLab() {
	}
	
	public ResearchLab(int id, int papersPublished, int age, String name, String topic) {
		this.id = id;
		this.papersPublished = papersPublished;
		this.age = age;
		this.name = name;
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPapersPublished() {
		return papersPublished;
	}

	public void setPapersPublished(int papersPublished) {
		this.papersPublished = papersPublished;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void addStudent(Student student) {
		this.Students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.Students.remove(student);
	}

	public void addProfessor(Professor Professor) {
		this.Professors.add(Professor);
	}
	
	public void removeProfessor(Professor Professor) {
		this.Professors.remove(Professor);
	}
	
	@Override
	public String toString() {
	    return "" + this.id + " " + this.papersPublished + " " + this.age + " " + this.name + " " + this.topic;
	}
}