package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.DAO.ICreatableWithRelationship;
import com.laba.solvd.database.Model.Professor;
import com.laba.solvd.database.Model.UniClass;

public interface ProfessorMapper extends ICreatableWithRelationship<Professor, UniClass> {

    @Insert("INSERT INTO professor(name, age, Class_id) VALUES(#{professor.name}, #{professor.age}, #{uniclass.id})")
    void create(@Param("professor") Professor professor, @Param("uniclass") UniClass uniClass);

    @Select("SELECT * FROM professor")
    List<Professor> selectAll();

    @Select("SELECT * FROM professor WHERE id = #{id}")
    Professor selectById(int id);

    @Delete("DELETE FROM professor WHERE id=#{id}")
    void delete(Professor professor);

    @Update("UPDATE professor set name=#{name}, age=#{age} WHERE id=#{id}")
    void update(Professor professor);

    @Update("UPDATE professor set name=#{professor.name}, age=#{professor.age}, Class_id=#{uniclass.id} WHERE id=#{professor.id}")
    void updateWithRelationship(@Param("professor") Professor professor,@Param("uniclass") UniClass uniClass);
}
