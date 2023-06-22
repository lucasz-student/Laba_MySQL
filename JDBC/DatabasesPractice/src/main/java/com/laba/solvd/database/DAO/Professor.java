package com.laba.solvd.database.DAO;

import java.util.ArrayList;
import java.util.List;

public class Professor {

	private int id;
	private String name; 
	private int age; 
	private UniClass Class;

	private List<Student> Students = new ArrayList<>();
	private List<ResearchLab> ResearchLabs = new ArrayList<>();
	
	public Professor() {
	}
	
	public Professor(int id, String name, int age, UniClass C) {
		this.id=id; 
		this.name=name;
		this.age=age;
		this.Class = C;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public UniClass getUniClass() {
		return this.Class;
	}

	public void setUniClass(UniClass class1) {
		this.Class = class1;
	}
	
	public void addStudent(Student student) {
		this.Students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.Students.remove(student);
	}
	
	public void addResearchLab(ResearchLab lab) {
		this.ResearchLabs.add(lab);
	}
	
	public void removeResearchLab(ResearchLab lab) {
		this.ResearchLabs.remove(lab);
	}
	
	@Override
	public String toString() {
	    return id + " " + name + " " + age;
	}
}
