package com.mrdelicious.minitankswot.rosters.databaseStuff;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mrdelicious.minitankswot.rosters.databaseStuff.TanksInRosters;

import java.util.List;

@Dao
public interface TanksInRostersDao {
    @Query("SELECT * FROM tanksInRosters")
    List<TanksInRosters> getAll();

    @Query("SELECT * FROM tanksInRosters WHERE roster_id = :id")
    List<TanksInRosters> findByRosterID(long id);

    @Query("SELECT * FROM tanksInRosters WHERE id = :tankId")
    TanksInRosters findTankByID(long tankId);

    @Insert
    long insertNew(TanksInRosters tanksInRosters);

    @Delete
    void delete(TanksInRosters tanksInRosters);
}
