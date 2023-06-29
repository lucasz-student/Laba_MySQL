package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.DAO.ICreatableWithRelationship;
import com.laba.solvd.database.Model.Gym;
import com.laba.solvd.database.Model.Student;

public interface StudentMapper extends ICreatableWithRelationship<Student, Gym> {
    
	@Insert("INSERT INTO Student(name, yearJoined, major, Gym_id) VALUES(#{student.name}, #{student.yearJoined}, #{student.major}, #{gym.id})")
	void create(@Param("student") Student student, @Param("gym") Gym gym);

    @Select("SELECT * FROM student")
    List<Student> selectAll();
    
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student selectById(int id);
    
    @Delete("DELETE FROM student WHERE id=#{id}")
    void delete(Student student);
    
    @Update("UPDATE student set name=#{name}, yearJoined=#{yearJoined}, major=#{major} WHERE id=#{id}")
    void update(Student student);
}