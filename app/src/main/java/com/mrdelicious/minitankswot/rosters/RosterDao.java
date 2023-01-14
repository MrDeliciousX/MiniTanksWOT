package com.mrdelicious.minitankswot.rosters;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RosterDao {
    @Query("SELECT * FROM rosters")
    List<Roster> getAll();

    @Query("SELECT name FROM rosters")
    List<String> getAllNames();

    @Query("SELECT * FROM rosters WHERE name = :name")
    Roster findByName(String name);

    @Query("SELECT * FROM rosters WHERE id = :id")
    Roster findByID(long id);

    @Insert
    long insertNew(Roster roster);

    @Delete
    void delete(Roster roster);
}
