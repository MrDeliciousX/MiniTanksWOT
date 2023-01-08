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
}
