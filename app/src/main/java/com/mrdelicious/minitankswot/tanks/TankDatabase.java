package com.mrdelicious.minitankswot.tanks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Tank.class}, version = 1)
public abstract class TankDatabase extends RoomDatabase {
    public abstract TankDao tankDao();
}
