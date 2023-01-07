package com.mrdelicious.minitankswot.rosters;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Roster.class}, version = 1)
public abstract class RostersDatabase extends RoomDatabase {
    public abstract RosterDao rosterDao();
}
