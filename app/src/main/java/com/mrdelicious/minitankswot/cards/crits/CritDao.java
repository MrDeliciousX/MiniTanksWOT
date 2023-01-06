package com.mrdelicious.minitankswot.cards.crits;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CritDao {
    @Query("SELECT * FROM crits")
    List<Crit> getAll();

    @Query("SELECT crit_name FROM crits")
    List<String> getAllNames();

    @Query("SELECT * FROM crits WHERE crit_name = :name")
    Crit findByName(String name);
}
