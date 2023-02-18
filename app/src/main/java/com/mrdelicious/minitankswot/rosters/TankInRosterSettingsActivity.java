package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.upgrades.Upgrade;

import java.util.List;

public class TankInRosterSettingsActivity extends AppCompatActivity {

    EverythingDatabase db;
    long rosterID, tankID;
    Roster roster;
    TanksInRosters tank;
    TextView tankName, tankPts;
    RecyclerView crew, modules, ammo, cons, eq;
    int totalPts;
    List<Upgrade> upgrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_in_roster_settings);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        rosterID = clicked.getLong("id");
        tankID = clicked.getLong("tankId");
        roster = db.rosterDao().findByID(rosterID);
        tank = db.tanksInRostersDao().findTankByID(tankID);
        upgrades = db.upgradeDao().getAll();

        this.setTitle("Modyfikacja czołgu");
        tankName = findViewById(R.id.tankRosterSetting_tankName);
        tankName.setText(db.tankDao().findByID(tank.tankID).tankName);
        tankPts = findViewById(R.id.tankRosterSetting_tankPts);
        totalPts = db.tankDao().findByID(tank.tankID).tankCost;
        tankPts.setText(totalPts + "pkt");

        uzupelnianieOpcji();
    }

    void uzupelnianieOpcji() {
        crew = findViewById(R.id.tankRosterSetting_crewList);
        modules = findViewById(R.id.tankRosterSetting_modulesList);
        ammo = findViewById(R.id.tankRosterSetting_ammoList);
        cons = findViewById(R.id.tankRosterSetting_consList);
        eq = findViewById(R.id.tankRosterSetting_eqList);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RosterMainActivity.class);
        intent.putExtra("id", rosterID);
        startActivity(intent);
    }
}