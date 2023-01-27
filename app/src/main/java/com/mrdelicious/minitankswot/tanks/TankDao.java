package com.mrdelicious.minitankswot.tanks;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TankDao {
    @Query("SELECT * FROM tanks")
    List<Tank> getAll();

    @Query("SELECT tank_name FROM tanks")
    List<String> getAllNames();

    @Query("SELECT * FROM tanks WHERE tank_name = :name")
    Tank findByName(String name);

    @Query("SELECT tank_name FROM tanks WHERE type = :type")
    List<String> findNamesByType(String type);

    @Query("SELECT tank_cost FROM tanks WHERE type = :type")
    List<Integer> findCostsByType(String type);

    @Query("SELECT nation FROM tanks WHERE type = :type")
    List<String> findNationsByType(String type);

    @Query("SELECT * FROM tanks WHERE id = :id")
    Tank findByID(int id);
}
