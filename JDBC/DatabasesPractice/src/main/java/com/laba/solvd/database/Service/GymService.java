package com.laba.solvd.database.Service;

import java.util.List;

import com.laba.solvd.database.DAO.GymDAO;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Student;

public class GymService {
	
	private GymDAO GymDAO;
	
	public GymService(GymDAO GymDAO) {	
		this.GymDAO=GymDAO;
	}
	
	public void enrollNewGym(Gym Gym, Student student) {
		this.GymDAO.create(Gym);
		this.GymDAO.addStudentToGym(Gym, student);
	}

	public void createGym(Gym Gym) {
		this.GymDAO.create(Gym);
	}
	
	public Gym getGymByID(int Id) {
		return this.GymDAO.selectById(Id);
	}
	
	public List<Gym> getAllGyms() {
		return this.GymDAO.selectAll();
	}
	
	public void updateGym(Gym Gym) {
		this.GymDAO.update(Gym);
	}
	
	public void deleteGym(Gym Gym) {
		this.GymDAO.delete(Gym);
	}
	
	public void addStudentToGym(Student student, Gym gym) {
		this.GymDAO.addStudentToGym(gym, student);
	}
}