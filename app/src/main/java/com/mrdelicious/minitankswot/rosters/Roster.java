package com.mrdelicious.minitankswot.rosters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rostersPattern")
public class Roster {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "tank_name")
    @NonNull
    public String tankName;
    public String ammo1;
    public String ammo2;
    public String ammo3;
    @ColumnInfo(name = "module_radio")
    public String moduleRadio;
    @ColumnInfo(name = "module_engine")
    public String moduleEngine;
    @ColumnInfo(name = "module_gun")
    public String moduleGun;
    @ColumnInfo(name = "module_suspension")
    public String moduleSuspension;
    @ColumnInfo(name = "module_turret")
    public String moduleTurret;
    public String equipment1;
    public String equipment2;
    public String equipment3;
    public String consumable1;
    public String consumable2;
    public String consumable3;
    @ColumnInfo(name = "crew_commander")
    public String crewCommander;
    @ColumnInfo(name = "crew_driver")
    public String crewDriver;
    @ColumnInfo(name = "crew_radio_operator")
    public String crewRadioOperator;
    @ColumnInfo(name = "crew_gunner")
    public String crewGunner;
    @ColumnInfo(name = "crew_loader")
    public String crewLoader;

    @Override
    public String toString() {
        return "Roster{" +
                "id=" + id +
                ", tankName='" + tankName + '\'' +
                ", ammo1='" + ammo1 + '\'' +
                ", ammo2='" + ammo2 + '\'' +
                ", ammo3='" + ammo3 + '\'' +
                ", moduleRadio='" + moduleRadio + '\'' +
                ", moduleEngine='" + moduleEngine + '\'' +
                ", moduleGun='" + moduleGun + '\'' +
                ", moduleSuspension='" + moduleSuspension + '\'' +
                ", moduleTurret='" + moduleTurret + '\'' +
                ", equipment1='" + equipment1 + '\'' +
                ", equipment2='" + equipment2 + '\'' +
                ", equipment3='" + equipment3 + '\'' +
                ", consumable1='" + consumable1 + '\'' +
                ", consumable2='" + consumable2 + '\'' +
                ", consumable3='" + consumable3 + '\'' +
                ", crewCommander='" + crewCommander + '\'' +
                ", crewDriver='" + crewDriver + '\'' +
                ", crewRadioOperator='" + crewRadioOperator + '\'' +
                ", crewGunner='" + crewGunner + '\'' +
                ", crewLoader='" + crewLoader + '\'' +
                '}';
    }
}
