package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.DAO.ICreatableNoRelationship;
import com.laba.solvd.database.Model.ResearchLab;

public interface ResearchLabMapper extends ICreatableNoRelationship<ResearchLab> {

    @Insert("INSERT INTO researchlab(papersPublished, age, name, topic) VALUES(#{papersPublished}, #{age}, #{name}, #{topic})")
    void create(ResearchLab researchLab);

    @Select("SELECT * FROM researchlab")
    List<ResearchLab> selectAll();

    @Select("SELECT * FROM researchlab WHERE id = #{id}")
    ResearchLab selectById(int id);

    @Delete("DELETE FROM researchlab WHERE id=#{id}")
    void delete(ResearchLab researchLab);

    @Update("UPDATE researchlab SET papersPublished=#{papersPublished}, age=#{age}, name=#{name}, topic=#{topic} WHERE id=#{id}")
    void update(ResearchLab researchLab);
}
