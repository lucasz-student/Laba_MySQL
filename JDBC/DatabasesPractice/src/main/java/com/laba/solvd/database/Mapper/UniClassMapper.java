package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.Model.UniClass;

public interface UniClassMapper {

	@Insert("INSERT INTO class(name, subject) VALUES (#{name}, #{subject})")
	void create(UniClass uniclass);
	
	@Select("SELECT * FROM class WHERE id = #{id}")
	UniClass selectById(int Id); 
	
	@Select("SELECT * FROM class")
	List<UniClass> selectAll();
	
	@Update("UPDATE class set name=#{name}, subject=#{subject} WHERE id=#{id}")
	void update(UniClass uniclass); 
	
	@Delete("DELETE FROM class WHERE id = #{id}")
	void delete(UniClass uniclass);
}