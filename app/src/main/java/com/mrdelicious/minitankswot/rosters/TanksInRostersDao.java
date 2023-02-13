package com.mrdelicious.minitankswot.rosters;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TanksInRostersDao {
    @Query("SELECT * FROM tanksInRosters")
    List<TanksInRosters> getAll();

    @Query("SELECT * FROM tanksInRosters WHERE roster_id = :id")
    List<TanksInRosters> findByRosterID(long id);

    @Insert
    long insertNew(TanksInRosters tanksInRosters);

    @Delete
    void delete(TanksInRosters tanksInRosters);
}
