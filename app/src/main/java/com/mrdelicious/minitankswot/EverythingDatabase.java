package com.mrdelicious.minitankswot;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mrdelicious.minitankswot.cards.crits.Crit;
import com.mrdelicious.minitankswot.cards.crits.CritDao;
import com.mrdelicious.minitankswot.cards.upgrades.Upgrade;
import com.mrdelicious.minitankswot.cards.upgrades.UpgradeDao;
import com.mrdelicious.minitankswot.rosters.databaseStuff.Roster;
import com.mrdelicious.minitankswot.rosters.databaseStuff.RosterDao;
import com.mrdelicious.minitankswot.rosters.databaseStuff.TanksInRosters;
import com.mrdelicious.minitankswot.rosters.databaseStuff.TanksInRostersDao;
import com.mrdelicious.minitankswot.rules.abilities.Ability;
import com.mrdelicious.minitankswot.rules.abilities.AbilityDao;
import com.mrdelicious.minitankswot.rules.terrains.Terrain;
import com.mrdelicious.minitankswot.rules.terrains.TerrainDao;
import com.mrdelicious.minitankswot.tanks.Tank;
import com.mrdelicious.minitankswot.tanks.TankDao;

@Database(entities = {Tank.class, Ability.class, Terrain.class, Crit.class, Roster.class, Upgrade.class, TanksInRosters.class}, version = 1)
public abstract class EverythingDatabase extends RoomDatabase {
    public abstract TankDao tankDao();
    public abstract AbilityDao abilityDao();
    public abstract TerrainDao terrainDao();
    public abstract CritDao critDao();
    public abstract RosterDao rosterDao();
    public abstract UpgradeDao upgradeDao();
    public abstract TanksInRostersDao tanksInRostersDao();
}
