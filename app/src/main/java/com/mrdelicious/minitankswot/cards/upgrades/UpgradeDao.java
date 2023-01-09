package com.mrdelicious.minitankswot.cards.upgrades;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UpgradeDao {
    @Query("SELECT * FROM upgrades")
    List<Upgrade> getAll();

    @Query("SELECT card_name FROM upgrades")
    List<String> getAllNames();

    @Query("SELECT * FROM upgrades WHERE card_name = :name")
    Upgrade findByName(String name);
}
