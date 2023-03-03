package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.upgrades.Upgrade;
import com.mrdelicious.minitankswot.rosters.databaseStuff.Roster;
import com.mrdelicious.minitankswot.rosters.databaseStuff.TanksInRosters;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMain;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMainAdapter;

import java.util.ArrayList;
import java.util.List;

public class TankInRosterSettingsActivity extends AppCompatActivity {

    EverythingDatabase db;
    long rosterID, tankID;
    Roster roster;
    TanksInRosters tank;
    TextView tankName, tankPts;
    RecyclerView crew, modules, ammo, cons, eq;
    RecyclerView.Adapter crewAdapter, modulesAdapter, ammoAdapter, consAdapter, eqAdapter;
    RecyclerView.LayoutManager crewManager, modulesManager, ammoManager, consManager, eqManager;
    int totalPts;
    List<Upgrade> upgrades;
    List<UpgradesMain> crewList, modulesList, ammoList, consList, eqList;
    String[] zalogaRozbite;

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

        listaModulow();
        listaAmunicji();
        listaMatEksp();
        listaWyposazenia();
        listaZalogi();
        uzupelnianieOpcji();
    }

    void listaModulow() {
        UpgradesMain modul;
        modulesList = new ArrayList<>();

        modul = new UpgradesMain("Silnik", 0);
        modulesList.add(modul);
        modul = new UpgradesMain("Działo", 0);
        modulesList.add(modul);
        modul = new UpgradesMain("Radio", 0);
        modulesList.add(modul);
        modul = new UpgradesMain("Zawieszenie", 0);
        modulesList.add(modul);
        if (!db.tankDao().findByID(tank.tankID).abilities.contains("Haubica")) {
            modul = new UpgradesMain("Wieża", 0);
            modulesList.add(modul);
        }
    }

    void listaZalogi() {
        String zaloga = db.tankDao().findByID(tank.tankID).crew;
        zalogaRozbite = zaloga.split("-");

        UpgradesMain zalogant;
        crewList = new ArrayList<>();

        for (String s : zalogaRozbite) {
            StringBuilder helper = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                char znak = s.charAt(j);

                if (j > 0) helper.append("/");

                switch (znak) {
                    case 'D':
                        helper.append("Dowódca");
                        break;
                    case 'C':
                        helper.append("Celowniczy");
                        break;
                    case 'R':
                        helper.append("Radiooperator");
                        break;
                    case 'K':
                        helper.append("Kierowca");
                        break;
                    case 'Ł':
                        helper.append("Ładowniczy");
                        break;
                }
            }
            zalogant = new UpgradesMain(helper.toString(), 0);
            crewList.add(zalogant);
        }
    }

    void listaAmunicji() {
        UpgradesMain amunicja;
        ammoList = new ArrayList<>();

        amunicja = new UpgradesMain("Amunicja: slot 1", 0);
        ammoList.add(amunicja);
        amunicja = new UpgradesMain("Amunicja: slot 2", 0);
        ammoList.add(amunicja);
        amunicja = new UpgradesMain("Amunicja: slot 3", 0);
        ammoList.add(amunicja);
    }

    void listaMatEksp() {
        UpgradesMain matEksp;
        consList = new ArrayList<>();

        matEksp = new UpgradesMain("Mat. Eksploatacyjne: slot 1", 0);
        consList.add(matEksp);
        matEksp = new UpgradesMain("Mat. Eksploatacyjne: slot 2", 0);
        consList.add(matEksp);
        matEksp = new UpgradesMain("Mat. Eksploatacyjne: slot 3", 0);
        consList.add(matEksp);
    }

    void listaWyposazenia() {
        UpgradesMain wyposazenie;
        eqList = new ArrayList<>();

        wyposazenie = new UpgradesMain("Wyposażenie: slot 1", 0);
        eqList.add(wyposazenie);
        wyposazenie = new UpgradesMain("Wyposażenie: slot 2", 0);
        eqList.add(wyposazenie);
        wyposazenie = new UpgradesMain("Wyposażenie: slot 3", 0);
        eqList.add(wyposazenie);
    }

    void uzupelnianieOpcji() {
        crew = findViewById(R.id.tankRosterSetting_crewList);
        modules = findViewById(R.id.tankRosterSetting_modulesList);
        ammo = findViewById(R.id.tankRosterSetting_ammoList);
        cons = findViewById(R.id.tankRosterSetting_consList);
        eq = findViewById(R.id.tankRosterSetting_eqList);

        crew.setHasFixedSize(true);
        modules.setHasFixedSize(true);
        ammo.setHasFixedSize(true);
        cons.setHasFixedSize(true);
        eq.setHasFixedSize(true);

        crewManager = new LinearLayoutManager(this);
        modulesManager = new LinearLayoutManager(this);
        ammoManager = new LinearLayoutManager(this);
        consManager = new LinearLayoutManager(this);
        eqManager = new LinearLayoutManager(this);
        crew.setLayoutManager(crewManager);
        modules.setLayoutManager(modulesManager);
        ammo.setLayoutManager(ammoManager);
        cons.setLayoutManager(consManager);
        eq.setLayoutManager(eqManager);

        crewAdapter = new UpgradesMainAdapter(crewList,
                TankInRosterSettingsActivity.this,
                "zaloga",
                false,
                rosterID,
                tankID);
        crew.setAdapter(crewAdapter);

        modulesAdapter = new UpgradesMainAdapter(modulesList,
                TankInRosterSettingsActivity.this,
                "modul",
                false,
                rosterID,
                tankID);
        modules.setAdapter(modulesAdapter);

        ammoAdapter = new UpgradesMainAdapter(ammoList,
                TankInRosterSettingsActivity.this,
                "ulepszenie/ammo",
                false,
                rosterID,
                tankID);
        ammo.setAdapter(ammoAdapter);

        consAdapter = new UpgradesMainAdapter(consList,
                TankInRosterSettingsActivity.this,
                "ulepszenie/mateksp",
                false,
                rosterID,
                tankID);
        cons.setAdapter(consAdapter);

        eqAdapter = new UpgradesMainAdapter(eqList,
                TankInRosterSettingsActivity.this,
                "ulepszenie/wyposazenie",
                false,
                rosterID,
                tankID);
        eq.setAdapter(eqAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RosterMainActivity.class);
        intent.putExtra("id", rosterID);
        startActivity(intent);
    }
}