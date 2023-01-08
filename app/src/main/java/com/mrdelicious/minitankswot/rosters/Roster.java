package com.mrdelicious.minitankswot.rosters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rosters")
public class Roster {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String name;
    @ColumnInfo(name = "limit_pts")
    public int limitPts;

    @Override
    public String toString() {
        return "Roster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limitPts=" + limitPts +
                '}';
    }
}
