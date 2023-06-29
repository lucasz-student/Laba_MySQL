package com.laba.solvd.database.Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Gym")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gym {

	@XmlAttribute
	private int id;
	
	@XmlElement(name = "Location")
	private String location;
	
	@XmlElement(name = "price")
	private int price;
	
	@XmlElement(name = "gymAge")
	private int gymAge;
	
	@XmlElement(name = "dateRegistered")
	private XMLGregorianCalendar dateRegistered;
	
	@XmlElementWrapper(name = "Students")
	@XmlElement(name = "Student")
	private List<Student> students = new ArrayList<>();

	public Gym() {
	}

	public Gym(int id, String location, int price, int gymAge) {
		this.id = id;
		this.location = location;
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
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public XMLGregorianCalendar getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(XMLGregorianCalendar dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public void printStudents() {
		this.students.forEach((student) -> {System.out.println(student);});
	}

	@Override
	public String toString() {
		return "" + this.id + " " + this.location + " " + this.gymAge + " " + this.price + " " + this.dateRegistered;
	}
}