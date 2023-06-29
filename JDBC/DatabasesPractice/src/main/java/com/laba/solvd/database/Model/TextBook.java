package com.laba.solvd.database.Model;

public class TextBook {

	private int id;
	private String name;
	private String bookCondition;
	
	private Student student;
	private UniClass Class;
	
	public TextBook() {
	}

	public TextBook(int id, String name, String bookCondition) {
		this.id = id;
		this.name = name;
		this.bookCondition = bookCondition;
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

	public String getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public UniClass getUniClass() {
		return Class;
	}

	public void setUniClass(UniClass class1) {
		Class = class1;
	}
	
	@Override
	public String toString() {
	    return "" + this.id + " " + this.name + " " + this.bookCondition;
	}
}