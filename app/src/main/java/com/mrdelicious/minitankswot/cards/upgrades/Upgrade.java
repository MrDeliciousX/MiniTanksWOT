package com.mrdelicious.minitankswot.cards.upgrades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "upgrades")
public class Upgrade {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "card_name")
    @NonNull
    public String upgradeName;
    @ColumnInfo(name = "card_cost")
    @NonNull
    public int upgradeCost;
    @NonNull
    public int official;
    @NonNull
    public String type;
    @NonNull
    public String text;
    public String nation;
    @ColumnInfo(name = "only_for")
    public String onlyFor;
    @NonNull
    public int visible;
}
