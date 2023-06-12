package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.SportsTeam;
import com.laba.solvd.database.DAO.SportsTeamDAO;

public class SportsTeamService {

	private SportsTeamDAO SportsTeamDAO;
	
	public SportsTeamService(SportsTeamDAO SportsTeam) {	
		this.SportsTeamDAO=SportsTeam;
	}
	
	public void createSportsTeam(SportsTeam sportsTeam) {
		this.SportsTeamDAO.createSportsTeam(sportsTeam);
	}
	
	public SportsTeam getSportsTeamByID(int Id) {
		return this.SportsTeamDAO.getSportsTeamById(Id);
	}
	
	public List<SportsTeam> getAllClasses() {
		return this.SportsTeamDAO.getAllSportsTeams();
	}
	
	public void updateSportsTeam(SportsTeam SportsTeam) {
		this.SportsTeamDAO.updateSportsTeam(SportsTeam);
	}
	
	public void deleteClass(SportsTeam SportsTeam) {
		this.SportsTeamDAO.deleteSportsTeam(SportsTeam);
	}
}
