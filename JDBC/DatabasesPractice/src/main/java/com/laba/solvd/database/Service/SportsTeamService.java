package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.SportsTeamDAO;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;

public class SportsTeamService {

	private SportsTeamDAO SportsTeamDAO;
	
	public SportsTeamService(SportsTeamDAO SportsTeam) {	
		this.SportsTeamDAO=SportsTeam;
	}
	
	public void enrollNewSportsTeam(SportsTeam sportsTeam, Student student) {
		this.SportsTeamDAO.create(sportsTeam);
		this.SportsTeamDAO.addStudentToSportsTeam(sportsTeam, student);
	}
	
	public void createSportsTeam(SportsTeam sportsTeam) {
		this.SportsTeamDAO.create(sportsTeam);
	}
	
	public SportsTeam getSportsTeamByID(int Id) {
		return this.SportsTeamDAO.selectById(Id);
	}
	
	public List<SportsTeam> getAllClasses() {
		return this.SportsTeamDAO.selectAll();
	}
	
	public void updateSportsTeam(SportsTeam SportsTeam) {
		this.SportsTeamDAO.update(SportsTeam);
	}
	
	public void deleteClass(SportsTeam SportsTeam) {
		this.SportsTeamDAO.delete(SportsTeam);
	}
}