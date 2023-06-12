package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.Gym;
import com.laba.solvd.database.DAO.GymDAO;

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
	
	public List<Gym> getAllGyms() {
		return this.GymDAO.getAllGyms();
	}
	
	public void updateGym(Gym Gym) {
		this.GymDAO.updateGym(Gym);
	}
	
	public void deleteGym(Gym Gym) {
		this.GymDAO.deleteGym(Gym);
	}
}
