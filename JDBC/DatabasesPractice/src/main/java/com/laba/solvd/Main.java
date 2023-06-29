package com.laba.solvd;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import com.laba.solvd.database.DAO.GymDAO;
import com.laba.solvd.database.Service.GymService;

public class Main {

	private static final String GET_BY_ID = "SELECT * FROM %s WHERE id=?";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		String st = "gym";
		System.out.println(String.format(GET_BY_ID, st));
		GymService gs = new GymService(new GymDAO());
		Arrays.asList(gs.getAllGyms()).forEach((p) -> p.forEach((s) -> System.out.println(s.getLocation())));}
}
