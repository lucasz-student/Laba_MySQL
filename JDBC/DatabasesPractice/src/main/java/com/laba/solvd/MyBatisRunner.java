package com.laba.solvd;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.laba.solvd.database.Mapper.GymMapper;
import com.laba.solvd.database.Mapper.ProfessorMapper;
import com.laba.solvd.database.Mapper.ResearchLabMapper;
import com.laba.solvd.database.Mapper.SportsTeamMapper;
import com.laba.solvd.database.Mapper.StudentMapper;
import com.laba.solvd.database.Mapper.TextBookMapper;
import com.laba.solvd.database.Mapper.UniClassMapper;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.ResearchLab;
import com.laba.solvd.database.Model.SportsTeam;
import com.laba.solvd.database.Model.Student;
import com.laba.solvd.database.Model.TextBook;
import com.laba.solvd.database.Model.UniClass;

public class MyBatisRunner {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        InputStream dbPropertiesStream = Resources.getResourceAsStream("db.properties");
        Properties dbProperties = new Properties();
        dbProperties.load(dbPropertiesStream);
		
        Properties properties = new Properties();
        properties.setProperty("driver", dbProperties.getProperty("db.driver"));
        properties.setProperty("url", dbProperties.getProperty("db.url"));
        properties.setProperty("username", dbProperties.getProperty("db.user"));
        properties.setProperty("password", dbProperties.getProperty("db.password"));
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			
			StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            Student student = new Student();
            student.setName("John");
            student.setYearJoined(2021);
            student.setMajor("Computer Science");
            Gym gym = new Gym(2, "BHall", 12, 53);
            studentMapper.create(student, gym);
            List<Student> students = studentMapper.selectAll();
            System.out.println(students);
            Student selectedStudent = studentMapper.selectById(1);
            System.out.println(selectedStudent);
            studentMapper.delete(selectedStudent);
            selectedStudent.setName("Updated Name");
            selectedStudent.setMajor("Updated Major");
            studentMapper.update(selectedStudent);
            
            GymMapper gymMapper = session.getMapper(GymMapper.class);
            Gym gym2 = new Gym();
            gym.setLocation("Gym Location");
            gym.setGymAge(5);
            gym.setPrice(100);
            gymMapper.create(gym2);
            List<Gym> gyms = gymMapper.selectAll();
            System.out.println(gyms);
            int gymId = gym.getId();
            Gym selectedGym = gymMapper.selectById(gymId);
            System.out.println(selectedGym);
            gymMapper.delete(gym2);
            List<Gym> gyms2 = gymMapper.selectAll();
            System.out.println(gyms2);
            selectedGym.setLocation("Updated Location");
            selectedGym.setGymAge(6);
            selectedGym.setPrice(200);
            gymMapper.update(selectedGym);
            System.out.println(selectedGym);
            
            ProfessorMapper professorMapper = session.getMapper(ProfessorMapper.class);

            UniClass uniClass = new UniClass(1, "physics", "basic Physics");
            Professor professor = new Professor();
            professor.setName("John Doe");
            professor.setAge(40);
            professorMapper.create(professor, uniClass);
            List<Professor> professors = professorMapper.selectAll();
            System.out.println(professors);
            Professor selectedProfessor = professorMapper.selectById(1);
            System.out.println(selectedProfessor);
            professorMapper.delete(selectedProfessor);
            List<Professor> professorsU = professorMapper.selectAll();
            System.out.println(professorsU);
            selectedProfessor.setName("Updated Name");
            selectedProfessor.setAge(45);
            professorMapper.update(selectedProfessor);
            System.out.println(selectedProfessor);

            ResearchLabMapper researchLabMapper = session.getMapper(ResearchLabMapper.class);
            researchLabMapper.create(new ResearchLab(1, 10, 2, "myLab", "associated sciences"));
            ResearchLab selectedLab = researchLabMapper.selectById(1);
            System.out.println(selectedLab);
            List<ResearchLab> ResearchLabs = researchLabMapper.selectAll();
            System.out.println(ResearchLabs);
            researchLabMapper.delete(selectedLab);
            List<ResearchLab> ResearchLabsU = researchLabMapper.selectAll();
            System.out.println(ResearchLabsU);
            
            SportsTeamMapper sportsTeamMapper = session.getMapper(SportsTeamMapper.class);
            sportsTeamMapper.create(new SportsTeam(1, 45, "PickleBall"));
            SportsTeam selectedSportsTeam = sportsTeamMapper.selectById(1);
            System.out.println(selectedSportsTeam);
            List<SportsTeam> sportsTeams = sportsTeamMapper.selectAll();
            System.out.println(sportsTeams);
            sportsTeamMapper.delete(selectedSportsTeam);
            List<SportsTeam> sportsTeamsU = sportsTeamMapper.selectAll();
            System.out.println(sportsTeamsU);
            
            TextBookMapper textBookMapper = session.getMapper(TextBookMapper.class);
            
            textBookMapper.create(new TextBook(1, "College physics", "New"));
            TextBook selectedTextBook =  textBookMapper.selectById(1);
            System.out.println(selectedTextBook);
            textBookMapper.delete(selectedTextBook);
            System.out.println(textBookMapper.selectAll());
            
            UniClassMapper uniClassMapper = session.getMapper(UniClassMapper.class);
            
            uniClassMapper.create(new UniClass(1, "CS", "Object Oriented Programming"));
            UniClass selectedUniClass = uniClassMapper.selectById(1);
            System.out.println(selectedUniClass);
            uniClassMapper.delete(selectedUniClass);
            System.out.println(uniClassMapper.selectAll());
		}
	}
}