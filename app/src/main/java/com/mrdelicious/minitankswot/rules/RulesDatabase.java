package com.mrdelicious.minitankswot.rules;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.mrdelicious.minitankswot.rules.abilities.Ability;
import com.mrdelicious.minitankswot.rules.abilities.AbilityDao;
import com.mrdelicious.minitankswot.rules.terrains.Terrain;
import com.mrdelicious.minitankswot.rules.terrains.TerrainDao;

@Database(entities = {Ability.class, Terrain.class}, version = 1)
public abstract class RulesDatabase extends RoomDatabase {
    public abstract AbilityDao abilityDao();
    public abstract TerrainDao terrainDao();
}
