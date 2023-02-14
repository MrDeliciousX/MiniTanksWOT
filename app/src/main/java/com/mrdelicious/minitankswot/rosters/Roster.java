package com.mrdelicious.minitankswot.rosters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rosters")
public class Roster {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    public String name;
    @ColumnInfo(name = "limit_pts")
    @NonNull
    public int limitPts;
    @ColumnInfo(name = "current_pts")
    @NonNull
    public int currentPts;

    public String nation;
    public String tiers;
    @NonNull
    public int official;

    @Override
    public String toString() {
        return "Roster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limitPts=" + limitPts +
                ", currentPts=" + currentPts +
                ", nation='" + nation + '\'' +
                ", tiers='" + tiers + '\'' +
                ", official=" + official +
                '}';
    }
}
