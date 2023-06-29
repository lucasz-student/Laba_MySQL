package com.laba.solvd.database.Parsing;

import java.io.File;
import com.laba.solvd.database.DAO.Gym;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Unmarshalling {

	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Gym.class);
		Unmarshaller u  = context.createUnmarshaller();
		Gym gym = (Gym) u.unmarshal(new File("src/main/java/com/laba/solvd/database/localDBS/UnmarshallingObjects.xml"));
		System.out.println(gym);
		gym.printStudents();
	}
}