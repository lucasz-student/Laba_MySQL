package com.laba.solvd.database.Mapper;

import com.laba.solvd.database.Model.SportsTeam;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SportsTeamMapper {

    @Insert("INSERT INTO sportsteam (sportName, gamesPlayed) VALUES (#{sportName}, #{gamesPlayed})")
    void create(SportsTeam sportsTeam);

    @Select("SELECT * FROM sportsteam")
    List<SportsTeam> selectAll();

    @Select("SELECT * FROM sportsteam WHERE id = #{id}")
    SportsTeam selectById(int id);

    @Delete("DELETE FROM sportsteam WHERE id = #{id}")
    void delete(SportsTeam sportsTeam);

    @Update("UPDATE sportsteam SET sportName = #{sportName}, gamesPlayed = #{gamesPlayed} WHERE id = #{id}")
    void update(SportsTeam sportsTeam);
}
