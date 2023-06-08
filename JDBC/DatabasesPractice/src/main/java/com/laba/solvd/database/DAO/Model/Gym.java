package com.laba.solvd.database.DAO.Model;

import java.util.ArrayList;
import java.util.List;

public class Gym {

	private int id;
	private String Location;
	private int price;
	private int gymAge;
	
	private List<Student> Students = new ArrayList<>();

	public Gym() {
	}

	public Gym(int id, String location, int price, int gymAge) {
		this.id = id;
		this.Location = location;
		this.price = price;
		this.gymAge = gymAge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGymAge() {
		return gymAge;
	}

	public void setGymAge(int gymAge) {
		this.gymAge = gymAge;
	}
	
	public void addStudent(Student student) {
		this.Students.add(student);
	}
}
