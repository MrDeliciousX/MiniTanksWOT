package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.upgrades.Upgrade;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMain;
import com.mrdelicious.minitankswot.rosters.listsStuff.UpgradesMainAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_upgrade);

        Bundle chosen = getIntent().getExtras();
        name = chosen.getString("nazwa");
        type = chosen.getString("typ");

        db = App.getDB(this);
        this.setTitle(name);

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
                upgrade = new UpgradesMain(allUpgrades.get(i).upgradeName, String.valueOf(allUpgrades.get(i).upgradeCost));
                upgradesList.add(upgrade);
            }
        }
    }

    void showList() {
        list = findViewById(R.id.choseUpgrade_list);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        adapter = new UpgradesMainAdapter(upgradesList, ChoseUpgradeActivity.this, type);
        list.setAdapter(adapter);
    }
}