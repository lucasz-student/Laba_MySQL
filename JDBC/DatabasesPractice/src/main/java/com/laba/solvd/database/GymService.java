package com.laba.solvd.database;

import java.util.List;

import com.laba.solvd.database.DAO.GymDAO;
import com.laba.solvd.database.DAO.Model.Gym;

public class GymService {
	
	private GymDAO GymDAO;
	
	public GymService(GymDAO GymDAO) {	
		this.GymDAO=GymDAO;
	}
	
	public void createGym(Gym Gym) {
		this.GymDAO.createGym(Gym);
	}
	
	public Gym getGymByID(int Id) {
		return this.GymDAO.getGymById(Id);
	}
	
	public List<Gym> getAllProfessors() {
		return this.GymDAO.getAllGyms();
	}
	
	public void updateProfessor(Gym Gym) {
		this.GymDAO.updateGym(Gym);
	}
	
	public void deletProfessor(Gym Gym) {
		this.GymDAO.deleteGym(Gym);
	}
}
