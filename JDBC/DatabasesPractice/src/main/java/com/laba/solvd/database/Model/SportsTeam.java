package com.laba.solvd.database.Model;

import java.util.ArrayList;
import java.util.List;

public class SportsTeam {

	private int id; 
	private int gamesPlayed;
	private String sportName;
	
	private List<Student> Students = new ArrayList<>();
	
	public SportsTeam() {
	}

	public SportsTeam(int id, int gamesPlayed, String sportName) {
		this.id = id;
		this.gamesPlayed = gamesPlayed;
		this.sportName = sportName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	
	public void addStudent(Student student) {
		this.Students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.Students.remove(student);
	}
	
	@Override
	public String toString() {
	    return id + " " + gamesPlayed + " " + sportName;
	}
}