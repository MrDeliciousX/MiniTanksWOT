package com.mrdelicious.minitankswot.rosters;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tanksInRosters")
public class TanksInRosters {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "roster_id")
    @NonNull
    public long rosterID;
    @ColumnInfo(name = "tank_id")
    @NonNull
    public int tankID;
    @ColumnInfo(name = "upgrade_id_ammo1")
    @NonNull
    public int ammo1;
    @ColumnInfo(name = "upgrade_id_ammo2")
    @NonNull
    public int ammo2;
    @ColumnInfo(name = "upgrade_id_ammo3")
    @NonNull
    public int ammo3;
    @ColumnInfo(name = "upgrade_id_equipment1")
    @NonNull
    public int eq1;
    @ColumnInfo(name = "upgrade_id_equipment2")
    @NonNull
    public int eq2;
    @ColumnInfo(name = "upgrade_id_equipment3")
    @NonNull
    public int eq3;
    @ColumnInfo(name = "upgrade_id_consumable1")
    @NonNull
    public int con1;
    @ColumnInfo(name = "upgrade_id_consumable2")
    @NonNull
    public int con2;
    @ColumnInfo(name = "upgrade_id_consumable3")
    @NonNull
    public int con3;
    @ColumnInfo(name = "upgrade_id_module_radio")
    @NonNull
    public int radio;
    @ColumnInfo(name = "upgrade_id_module_engine")
    @NonNull
    public int engine;
    @ColumnInfo(name = "upgrade_id_module_gun")
    @NonNull
    public int gun;
    @ColumnInfo(name = "upgrade_id_module_suspension")
    @NonNull
    public int sus;
    @ColumnInfo(name = "upgrade_id_module_turret")
    @NonNull
    public int turret;
    @ColumnInfo(name = "upgrade_id_crew_commander")
    @NonNull
    public int commander;
    @ColumnInfo(name = "upgrade_id_crew_driver")
    @NonNull
    public int driver;
    @ColumnInfo(name = "upgrade_id_crew_radioOperator")
    @NonNull
    public int radioMan;
    @ColumnInfo(name = "upgrade_id_crew_gunner")
    @NonNull
    public int gunner;
    @ColumnInfo(name = "upgrade_id_crew_loader")
    @NonNull
    public int loader;

    @Override
    public String toString() {
        return "TanksInRosters{" +
                "id=" + id +
                ", rosterID=" + rosterID +
                ", tankID=" + tankID +
                ", ammo1=" + ammo1 +
                ", ammo2=" + ammo2 +
                ", ammo3=" + ammo3 +
                ", eq1=" + eq1 +
                ", eq2=" + eq2 +
                ", eq3=" + eq3 +
                ", con1=" + con1 +
                ", con2=" + con2 +
                ", con3=" + con3 +
                ", radio=" + radio +
                ", engine=" + engine +
                ", gun=" + gun +
                ", sus=" + sus +
                ", turret=" + turret +
                ", commander=" + commander +
                ", driver=" + driver +
                ", radioMan=" + radioMan +
                ", gunner=" + gunner +
                ", loader=" + loader +
                '}';
    }
}
