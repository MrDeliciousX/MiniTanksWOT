package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.tanks.Tank;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RosterMainActivity extends AppCompatActivity {

    EverythingDatabase db;
    long rosterID;
    List<TanksInRosters> findTanks;
    RecyclerView lt, mt, ht, td, spg;
    TextView ltCost, mtCost, htCost, tdCost, spgCost;
    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_main);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        rosterID = clicked.getLong("id");
        Roster roster = db.rosterDao().findByID(rosterID);

        this.setTitle(roster.name);

        lt = findViewById(R.id.rosterMain_ltList);
        mt = findViewById(R.id.rosterMain_mtList);
        ht = findViewById(R.id.rosterMain_htList);
        td = findViewById(R.id.rosterMain_tdList);
        spg = findViewById(R.id.rosterMain_spgList);
        ltCost = findViewById(R.id.rosterMain_ltPts);
        mtCost = findViewById(R.id.rosterMain_mtPts);
        htCost = findViewById(R.id.rosterMain_htPts);
        tdCost = findViewById(R.id.rosterMain_tdPts);
        spgCost = findViewById(R.id.rosterMain_spgPts);

        showTanksOnList("light",lt, ltCost);
        showTanksOnList("medium",mt, mtCost);
        showTanksOnList("heavy",ht, htCost);
        showTanksOnList("destroyer",td, tdCost);
        showTanksOnList("spg",spg, spgCost);
    }

    void showTanksOnList (String type, RecyclerView tankList, TextView pts) {
        findTanks = db.tanksInRostersDao().findByRosterID(rosterID);
        int ptsSum = 0;
        List<TankOnList> tanks = new ArrayList<>();
        for (int i = 0; i < findTanks.size(); i++) {
            Tank t = db.tankDao().findByID(findTanks.get(i).tankID);
            if (t.type.equals(type)) {
                TankOnList tank = new TankOnList(t.tankName, flagFill(t.nation), t.tankCost, tankImageFill(t.tankName));
                tanks.add(tank);
                ptsSum += t.tankCost;
            }
        }
        tanks.sort(TankOnList.TanksPtsComparator);

        tankList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        tankList.setLayoutManager(layoutManager);

        listAdapter = new TankOnListAdapter(tanks, RosterMainActivity.this, false, rosterID);
        tankList.setAdapter(listAdapter);

        pts.setText(ptsSum + "pkt");
    }

    int flagFill(String nation){
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("flag_");
        for (int i = 0; i < nation.length(); i++) {
            nameHelper.append(nation.charAt(i));
        }
        return getResources().getIdentifier(nameHelper.toString(), "drawable", getPackageName());
    }

    int tankImageFill(String name){
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("tank_");
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i)==' ' || name.charAt(i)=='-' || name.charAt(i)=='(' || name.charAt(i)==')' || name.charAt(i)=='.'){
                nameHelper.append('_');
            } else nameHelper.append(name.charAt(i));
        }
        nameHelper = new StringBuilder(nameHelper.toString().toLowerCase(Locale.ROOT));
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
        intent.putExtra("rosterID", rosterID);
        startActivity(intent);
    }
}