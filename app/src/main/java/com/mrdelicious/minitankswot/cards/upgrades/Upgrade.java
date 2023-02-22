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
    @ColumnInfo(name = "only_for_tanks")
    public String onlyForTanks;
    @NonNull
    public int visible;

    @ColumnInfo(name = "only_for_types")
    public String onlyForTypes;

    @ColumnInfo(name = "only_for_stats")
    public String onlyForStats;

    @ColumnInfo(name = "only_for_crew")
    public String onlyForCrew;
}
