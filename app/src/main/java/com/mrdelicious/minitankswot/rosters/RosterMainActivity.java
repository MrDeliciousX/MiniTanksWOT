package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.tanks.Tank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RosterMainActivity extends AppCompatActivity {

    EverythingDatabase db;
    long id;
    List<TanksInRosters> findTanks;
    RecyclerView lt, mt, ht, td, spg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_main);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        id = clicked.getLong("id");
        Roster roster = db.rosterDao().findByID(id);

        this.setTitle(roster.name);

        lt = findViewById(R.id.rosterMain_ltList);
        mt = findViewById(R.id.rosterMain_mtList);
        ht = findViewById(R.id.rosterMain_htList);
        td = findViewById(R.id.rosterMain_tdList);
        spg = findViewById(R.id.rosterMain_spgList);

        showTanksOnList("light",lt);
        showTanksOnList("medium",mt);
        showTanksOnList("heavy",ht);
        showTanksOnList("destroyer",td);
        showTanksOnList("spg",spg);
    }

    void setCosts () {
        TextView ltCost = findViewById(R.id.rosterMain_ltPts);
        TextView mtCost = findViewById(R.id.rosterMain_mtPts);
        TextView htCost = findViewById(R.id.rosterMain_htPts);
        TextView tdCost = findViewById(R.id.rosterMain_tdPts);
        TextView spgCost = findViewById(R.id.rosterMain_spgPts);
    }

    void showTanksOnList (String type, RecyclerView tankList) {
        findTanks = db.tanksInRostersDao().findByRosterID(id);
        ArrayList<TankOnList> tanks = new ArrayList<>();
        for (int i = 0; i < findTanks.size(); i++) {
            Tank t = db.tankDao().findByID(findTanks.get(i).tankID);
            if (t.type.equals(type)) {
                TankOnList tank = new TankOnList(t.tankName, t.tankCost, imageFill(t.nation));
                tanks.add(tank);
            }
        }
        Collections.sort(tanks);

        TankAdapter tankAdapter = new TankAdapter(RosterMainActivity.this, tanks, id, false);
        //tankList.setAdapter(tankAdapter);
    }

    int imageFill(String nation){
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("flag_");
        for (int i = 0; i < nation.length(); i++) {
            nameHelper.append(nation.charAt(i));
        }
        return getResources().getIdentifier(nameHelper.toString(), "drawable", getPackageName());
    }

    public void addLTs(View view) {
        openAddTanks("light");
    }

    public void addMTs(View view) {
        openAddTanks("medium");
    }

    public void addHTs(View view) {
        openAddTanks("heavy");
    }

    public void addTDs(View view) {
        openAddTanks("destroyer");
    }

    public void addSPGs(View view) {
        openAddTanks("spg");
    }

    void openAddTanks(String type) {
        Intent intent = new Intent(RosterMainActivity.this, AddTanksActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("rosterID", id);
        startActivity(intent);
    }
}