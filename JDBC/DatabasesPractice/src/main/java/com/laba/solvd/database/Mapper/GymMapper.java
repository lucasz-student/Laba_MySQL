package com.laba.solvd.database.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.laba.solvd.database.DAO.ICreatableNoRelationship;
import com.laba.solvd.database.Model.Gym;

public interface GymMapper extends ICreatableNoRelationship<Gym> {

    @Insert("INSERT INTO gym(Location, gymAge, price) VALUES(#{location}, #{gymAge}, #{price})")
    void create(Gym gym);
    
    @Select("SELECT * FROM gym")
    List<Gym> selectAll();
    
    @Select("SELECT * FROM gym WHERE id = #{id}")
    Gym selectById(int id);
    
    @Delete("DELETE FROM gym WHERE id = #{id}")
    void delete(Gym gym);
    
    @Update("UPDATE gym SET Location = #{location}, gymAge = #{gymAge}, price = #{price} WHERE id = #{id}")
    void update(Gym gym);
}
