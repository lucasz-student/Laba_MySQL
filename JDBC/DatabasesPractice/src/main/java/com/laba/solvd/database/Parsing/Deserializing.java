package com.laba.solvd.database.Parsing;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.solvd.database.DAO.Gym;
import com.laba.solvd.database.DAO.Professor;
import com.laba.solvd.database.DAO.SportsTeam;
import com.laba.solvd.database.DAO.TextBook;
import com.laba.solvd.database.DAO.UniClass;

public class Deserializing {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> nodes = mapper.readValue(
				new File("src/main/java/com/laba/solvd/database/localDBS/Objects.json"), 
				new TypeReference<List<JsonNode>>() {});
		
        JsonNode rootNode = mapper.readTree(new File("src/main/java/com/laba/solvd/database/localDBS/Objects.json"));
        Queue<String> JsonFieldNames = new LinkedList<String>();
        
        for (JsonNode node : rootNode) {
        	Iterator<String> fieldnames = node.fieldNames();
        	JsonFieldNames.add(fieldnames.next());
		}
        
        for (JsonNode node1 : nodes) {
        	String ObjectType = JsonFieldNames.poll();
			JsonNode Objects = node1.findValue(ObjectType);
        	switch (ObjectType) {
        	case ("Gyms"): 
        		Objects.forEach((n) -> {
        			System.out.println(
        					new Gym(n.findValue("id").asInt(), n.findValue("location").asText(), n.findValue("price").asInt(), n.findValue("gymAge").asInt()));
        					
        		});
        		break;
        	case ("Professors"): 
        		Objects.forEach((n) -> {
        			System.out.println(new Professor(n.findValue("id").asInt(), n.findValue("name").asText(), n.findValue("age").asInt(), null));
        		});
        		break;
        	case ("Sportsteams"): 
        		Objects.forEach((n) -> {
        			System.out.println(new SportsTeam(n.findValue("id").asInt(), n.findValue("gamesPlayed").asInt(), n.findValue("sportsName").asText()));
        		});
        		break;
        	case ("Textbooks"): 
        		Objects.forEach((n) -> {
        			System.out.println(new TextBook(n.findValue("id").asInt(), n.findValue("name").asText(), n.findValue("bookCondition").asText()));
        		});
        		break;
        	case ("Uniclasses"): 
        		Objects.forEach((n) -> {
        			System.out.println(new UniClass(n.findValue("id").asInt(), n.findValue("name").asText(), n.findValue("subject").asText()));
        		});
        		break;
        	}
        }
	}
}