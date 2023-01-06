package com.mrdelicious.minitankswot.rules.terrains;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terrains")
public class Terrain {
    @PrimaryKey
    public int id;
    @NonNull
    public String name;
    @NonNull
    public String text;
    @NonNull
    public int official;

    @Override
    public String toString() {
        return "Terrain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", official=" + official +
                '}';
    }
}
