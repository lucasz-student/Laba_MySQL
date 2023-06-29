package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.Model.TextBook;

public interface TextBookMapper {

    @Insert("INSERT INTO textbook(name, bookCondition) VALUES (#{textbook.name}, #{textbook.bookCondition})")
    void create(@Param("textbook") TextBook textBook);

    @Select("SELECT * FROM textbook")
    List<TextBook> selectAll();

    @Select("SELECT * FROM textbook WHERE id = #{id}")
    TextBook selectById(int id);

    @Delete("DELETE FROM textbook WHERE id = #{id}")
    void delete(TextBook textBook);

    @Update("UPDATE textbook set name=#{name}, Student_id=#{Student_id}, bookCondition=#{bookCondition} WHERE id=#{id}")
    void update(TextBook textBook);
}
