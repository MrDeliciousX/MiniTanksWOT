package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.upgrades.Upgrade;
import com.mrdelicious.minitankswot.rosters.databaseStuff.TanksInRosters;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMain;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMainAdapter;
import com.mrdelicious.minitankswot.tanks.Tank;

import java.util.ArrayList;
import java.util.List;

public class ChoseUpgradeActivity extends AppCompatActivity {
    String name, type;
    EverythingDatabase db;
    RecyclerView list;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<UpgradesMain> upgradesList;
    List<Upgrade> allUpgrades;
    long rosterId, tankId;
    TanksInRosters tanksInRosters;
    Tank tank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_upgrade);

        Bundle chosen = getIntent().getExtras();
        name = chosen.getString("nazwa");
        type = chosen.getString("typ");
        rosterId = chosen.getLong("id");
        tankId = chosen.getLong("tankId");

        db = App.getDB(this);
        this.setTitle(name);

        tanksInRosters = db.tanksInRostersDao().findTankByID(tankId);
        tank = db.tankDao().findByID(tanksInRosters.tankID);

        createList();
        showList();
    }

    void createList() {
        upgradesList = new ArrayList<>();
        allUpgrades = new ArrayList<>();
        UpgradesMain upgrade;

        allUpgrades = db.upgradeDao().getAll();
        for (int i = 0; i < allUpgrades.size(); i++) {
            if (allUpgrades.get(i).type.contains(type)) {
                switch (type) {
                    case "zaloga":
                        if (zalogant(i)) {
                            upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, allUpgrades.get(i).upgradeCost);
                            upgradesList.add(upgrade);
                        }
                        break;
                    case "modul":
                        if (modul(i)) {
                            upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, allUpgrades.get(i).upgradeCost);
                            upgradesList.add(upgrade);
                        }
                        break;
                    case "ulepszenie/ammo":
                        if (ammo(i)) {
                            upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, allUpgrades.get(i).upgradeCost);
                            upgradesList.add(upgrade);
                        }
                        break;
                    case "ulepszenie/mateksp":
                        if (matEksp(i)) {
                            upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, allUpgrades.get(i).upgradeCost);
                            upgradesList.add(upgrade);
                        }
                        break;
                    case "ulepszenie/wyposazenie":
                        if (wyposazenie(i)) {
                            upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, allUpgrades.get(i).upgradeCost);
                            upgradesList.add(upgrade);
                        }
                        break;
                }
            }
        }
        upgradesList.sort(UpgradesMain.UpgradesPtsComparator);
    }

    boolean zalogant(int i) {
        Upgrade upgrade = allUpgrades.get(i);
        if (upgrade.nation == null || upgrade.nation.contains(tank.nation)) {
            if (upgrade.onlyForCrew == null) return true;
            else return name.contains(upgrade.onlyForCrew);
        } else return false;
    }

    boolean modul(int i) {
        Upgrade upgrade = allUpgrades.get(i);
        if (upgrade.type.contains(name)) {
            return upgrade.onlyForTanks == null || upgrade.onlyForTanks.contains(tank.tankName);
        } else return false;
    }

    boolean ammo(int i) {
        Upgrade upgrade = allUpgrades.get(i);
        String stat = upgrade.onlyForStats;
        int statystyka = 0;
        switch (stat.charAt(0)) {
            case 's' :
                statystyka = tank.firepower;
                break;
            case 'o' :
                statystyka = tank.survivability;
        }
        int parseInt = Integer.parseInt(String.valueOf(stat.charAt(1)));
        switch (stat.charAt(2)) {
            case '+': return statystyka >= parseInt;
            case '-': return statystyka <= parseInt;
            default: return false;
        }
    }

    boolean matEksp(int i) {
        Upgrade upgrade = allUpgrades.get(i);
        return upgrade.nation == null || upgrade.nation.contains(tank.nation);
    }

    boolean wyposazenie(int i) {
        Upgrade upgrade = allUpgrades.get(i);
        if (upgrade.nation == null || upgrade.nation.contains(tank.nation)) {
            return upgrade.onlyForTypes == null || upgrade.onlyForTypes.contains(tank.type);
        } else return false;
    }

    void showList() {
        list = findViewById(R.id.choseUpgrade_list);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new UpgradesMainAdapter(upgradesList,
                ChoseUpgradeActivity.this,
                type,
                true,
                rosterId,
                tankId);
        list.setAdapter(adapter);
    }
}