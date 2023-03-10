package com.mrdelicious.minitankswot;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class App extends Application {
    public static EverythingDatabase getDB(Context context) {

        return Room.databaseBuilder(context, EverythingDatabase.class, "db_tanks.db")
                .allowMainThreadQueries()
                .createFromAsset("databases/db_tanks.db")
                .build();
    }
}
