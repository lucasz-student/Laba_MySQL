package com.laba.solvd.database.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
	
	@XmlAttribute
	private int id;
	@XmlElement(name = "yearJoined")
	private int yearJoined;
	@XmlElement(name = "name")
	private String name;  
	@XmlElement(name = "major")
	private String major;
	
	private Gym gym;

	private List<Professor> Professors = new ArrayList<>();
	private List<TextBook> textBooks = new ArrayList<>();
	private List<SportsTeam> SportsTeams = new ArrayList<>();
	private List<ResearchLab> ResearchLabs = new ArrayList<>();
	private List<UniClass> UniClasses = new ArrayList<>();
	
	public Student() {
	}
	
	public Student(int id, String name, int year, String major) {
		this.id = id;
		this.name = name; 
		this.major = major; 
		this.yearJoined = year;
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

	public int getYearJoined() {
		return yearJoined;
	}

	public void setYearJoined(int yearJoined) {
		this.yearJoined = yearJoined;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}
	
	public void addProfessor(Professor Professor) {
		this.Professors.add(Professor);
	}
	
	public void removeProfessor(Professor Professor) {
		this.Professors.remove(Professor);
	}
	
	public void addTextBook(TextBook textBook) {
		this.textBooks.add(textBook);
	}
	
	public void removeTextBook(TextBook textBook) {
		this.textBooks.remove(textBook);
	}
	
	public void addSportsTeam(SportsTeam SportsTeam) {
		this.SportsTeams.add(SportsTeam);
	}
	
	public void removeSportsTeam(SportsTeam SportsTeam) {
		this.SportsTeams.remove(SportsTeam);
	}
	
	public void addResearchLab(ResearchLab lab) {
		this.ResearchLabs.add(lab);
	}
	
	public void removeResearchLab(ResearchLab lab) {
		this.ResearchLabs.remove(lab);
	}
	
	public void addUniClass(UniClass UniClass) {
		this.UniClasses.add(UniClass);
	}
	
	public void removeUniClass(UniClass UniClass) {
		this.UniClasses.remove(UniClass);
	}
	
	@Override
	public String toString() {
		return "" + this.id + " " + this.name + " " + this.major + " " + this.yearJoined;
	}
}
