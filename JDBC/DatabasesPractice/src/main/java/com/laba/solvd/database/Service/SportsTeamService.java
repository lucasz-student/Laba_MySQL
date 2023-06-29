package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.SportsTeamDAO;
import com.laba.solvd.database.Mapper.SportsTeamMapper;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;

public class SportsTeamService extends AbstractService{

	private SportsTeamDAO SportsTeamDAO;
	private SportsTeamMapper sportsTeamMapper;
	
	public SportsTeamService(SportsTeamDAO SportsTeam) throws IOException {
		super();
		this.SportsTeamDAO=SportsTeam;
		this.sportsTeamMapper=this.getSession().getMapper(SportsTeamMapper.class);
	}
	
	public void enrollNewSportsTeam(SportsTeam sportsTeam, Student student) {
		sportsTeamMapper.create(sportsTeam);
		this.SportsTeamDAO.addStudentToSportsTeam(sportsTeam, student);
	}
	
	public void createSportsTeam(SportsTeam sportsTeam) {
		sportsTeamMapper.create(sportsTeam);
	}
	
	public SportsTeam getSportsTeamByID(int Id) {
		return sportsTeamMapper.selectById(Id);
	}
	
	public List<SportsTeam> getAllClasses() {
		return sportsTeamMapper.selectAll();
	}
	
	public void updateSportsTeam(SportsTeam SportsTeam) {
		sportsTeamMapper.update(SportsTeam);
	}
	
	public void deleteClass(SportsTeam SportsTeam) {
		sportsTeamMapper.delete(SportsTeam);
	}
}