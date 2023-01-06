package com.mrdelicious.minitankswot.rules.abilities;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AbilityDao {
    @Query("SELECT * FROM abilities")
    List<Ability> getAll();

    @Query("SELECT name FROM abilities")
    List<String> getAllNames();

    @Query("SELECT * FROM abilities WHERE name = :name")
    Ability findByName(String name);

    @Query("SELECT name FROM abilities WHERE is_official = :official")
    List<String> findByOfficial(int official);
}
