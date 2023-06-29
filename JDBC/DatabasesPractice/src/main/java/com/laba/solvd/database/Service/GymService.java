package com.laba.solvd.database.Service;

import java.io.IOException;
import java.util.List;

import com.laba.solvd.database.DAO.GymDAO;
import com.laba.solvd.database.Mapper.GymMapper;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Student;

public class GymService extends AbstractService {

	private GymDAO GymDAO;
	private GymMapper gymMapper;
	
	public GymService(GymDAO DAO) throws IOException {
		super();
		this.gymMapper = this.getSession().getMapper(GymMapper.class);
		this.GymDAO = DAO;
	}
	public void enrollNewGym(Gym Gym, Student student) {
		gymMapper.create(Gym);;
		this.GymDAO.addStudentToGym(Gym, student);
	}

	public void createGym(Gym Gym) {
		gymMapper.create(Gym);
	}
	
	public Gym getGymByID(int Id) {
		return gymMapper.selectById(Id);
	}
	
	public List<Gym> getAllGyms() {
		return gymMapper.selectAll();
	}
	
	public void updateGym(Gym Gym) {
		gymMapper.update(Gym);
	}
	
	public void deleteGym(Gym Gym) {
		gymMapper.delete(Gym);
	}
	
	public void addStudentToGym(Student student, Gym gym) {
		this.GymDAO.addStudentToGym(gym, student);
	}
}