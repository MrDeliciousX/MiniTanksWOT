package com.mrdelicious.minitankswot.rules.terrains;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TerrainDao {
    @Query("SELECT * FROM terrains")
    List<Terrain> getAll();

    @Query("SELECT name FROM terrains")
    List<String> getAllNames();

    @Query("SELECT * FROM terrains WHERE name = :name")
    Terrain findByName(String name);

    @Query("SELECT name FROM terrains WHERE official = :official")
    List<String> findByOfficial(int official);
}
