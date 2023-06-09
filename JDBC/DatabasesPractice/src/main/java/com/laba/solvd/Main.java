package com.laba.solvd;

import java.util.Arrays;

import com.laba.solvd.database.GymService;
import com.laba.solvd.database.DAO.GymDAO;

public class Main {

	public static void main(String[] args) {
	GymService gs = new GymService(new GymDAO());
	Arrays.asList(gs.getAllGyms()).forEach((p) -> p.forEach((s) -> System.out.println(s.getLocation())));
	}
}
