package com.mrdelicious.minitankswot.cards.crits;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crits")
public class Crit {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "crit_name")
    @NonNull
    public String critName;
    @NonNull
    public int damage;
    @NonNull
    public int repairable;
    public String text;
    @NonNull
    public int amount;

    @Override
    public String toString() {
        return "Crit{" +
                "id=" + id +
                ", critName='" + critName + '\'' +
                ", damage=" + damage +
                ", repairable=" + repairable +
                ", text='" + text + '\'' +
                ", amount=" + amount +
                '}';
    }
}
