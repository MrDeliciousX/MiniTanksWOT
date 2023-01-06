package com.mrdelicious.minitankswot.rules.abilities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "abilities")
public class Ability {
    @PrimaryKey
    public int id;
    @NonNull
    public String name;
    @NonNull
    public String text;
    @ColumnInfo(name = "is_official")
    @NonNull
    public int isOfficial;

    @Override
    public String toString() {
        return "Ability{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", isOfficial=" + isOfficial +
                '}';
    }
}
