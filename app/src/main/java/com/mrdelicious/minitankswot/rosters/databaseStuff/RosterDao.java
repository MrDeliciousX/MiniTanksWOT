package com.mrdelicious.minitankswot.rosters.databaseStuff;

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

    @Query("UPDATE rosters SET name = :nowaNazwa WHERE id = :id")
    void zmianaNazwy(String nowaNazwa, long id);

    @Query("UPDATE rosters SET limit_pts = :nowyLimit WHERE id = :id")
    void zmianaLimituPkt(int nowyLimit, long id);

    @Query("UPDATE rosters SET nation = :kraje WHERE id = :id")
    void zmianaKrajow(String kraje, long id);

    @Query("UPDATE rosters SET tiers = :tiery WHERE id = :id")
    void zmianaTierow(String tiery, long id);

    @Query("UPDATE rosters SET current_pts = :suma WHERE id = :id")
    void zmianaSumyPkt(int suma, long id);

    @Query("UPDATE rosters SET official = :czyOficjalny WHERE id = :id")
    void zmianaCzyOficjalny(int czyOficjalny, long id);
}