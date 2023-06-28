package com.laba.solvd;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.laba.solvd.database.Model.Student;
s
public class MyBatisRunner {

	public static void main(String[] args) throws IOException {
		String resource = "/DatabasesPractice/src/main/resources/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			Student student = session.selectOne("resources.mappers.studentMapper.selectStudentById", 1);
			syso
		}
	}
}
