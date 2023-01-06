package com.mrdelicious.minitankswot.tanks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tanks")
public class Tank {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "tank_name")
    @NonNull
    public String tankName;
    @ColumnInfo(name = "tank_cost")
    public int tankCost;
    @ColumnInfo(name = "is_official")
    public int isOfficial;
    public int firepower;
    public int survivability;
    public int mobility;
    public int initiative;
    public int hp;
    @ColumnInfo(name = "hp_colors")
    @NonNull
    public String hpColors;
    @NonNull
    public String crew;
    public int tier;
    @NonNull
    public String type;
    @NonNull
    public String abilities;
    @NonNull
    public String nation;
    public String history;

    @Override
    public String toString() {
        return "Tank{" +
                "id=" + id +
                ", tankName='" + tankName + '\'' +
                ", tankCost=" + tankCost +
                ", isOfficial=" + isOfficial +
                ", firepower=" + firepower +
                ", survivability=" + survivability +
                ", mobility=" + mobility +
                ", initiative=" + initiative +
                ", hp=" + hp +
                ", hpColors='" + hpColors + '\'' +
                ", crew='" + crew + '\'' +
                ", tier=" + tier +
                ", type='" + type + '\'' +
                ", abilities='" + abilities + '\'' +
                ", nation='" + nation + '\'' +
                ", history='" + history + '\'' +
                '}';
    }
}
