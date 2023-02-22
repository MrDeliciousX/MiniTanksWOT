package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.rosters.databaseStuff.Roster;
import com.mrdelicious.minitankswot.rosters.listsStuff.TankOnList;
import com.mrdelicious.minitankswot.rosters.listsStuff.TankOnListAdapter;
import com.mrdelicious.minitankswot.tanks.Tank;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddTanksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EverythingDatabase db;
    Long rosterID;
    Roster roster;
    String type;
    List<String> findTanks, nations;
    List<Integer> costs;
    RecyclerView recyclerView;
    RecyclerView.Adapter tankAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tanks);

        recyclerView = findViewById(R.id.addTanks_tankList);
        db = App.getDB(this);

        Bundle chosen = getIntent().getExtras();
        type = chosen.getString("type");
        rosterID = chosen.getLong("rosterID");
        roster = db.rosterDao().findByID(rosterID);
        findTanks = db.tankDao().findNamesByType(type);
        costs = db.tankDao().findCostsByType(type);
        nations = db.tankDao().findNationsByType(type);
        switch (type) {
            case "light":
                this.setTitle("Czołgi Lekkie");
                break;
            case "medium":
                this.setTitle("Czołgi Średnie");
                break;
            case "heavy":
                this.setTitle("Czołgi Ciężkie");
                break;
            case "destroyer":
                this.setTitle("Niszczyciele");
                break;
            case "spg":
                this.setTitle("Artyleria");
                break;
        }
        showTanksOnList();
    }


    void showTanksOnList () {
        List<TankOnList> tankOnList = new ArrayList<>();
        TankOnList tank;
        for (int i = 0; i < findTanks.size(); i++) {
            if (czolgPasuje(findTanks.get(i))) {
                tank = new TankOnList(findTanks.get(i), flagFill(nations.get(i)), costs.get(i), tankImageFill(findTanks.get(i)), -1);
                tankOnList.add(tank);
            }
        }
        tankOnList.sort(TankOnList.TanksPtsComparator);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        tankAdapter = new TankOnListAdapter(tankOnList, AddTanksActivity.this, true, rosterID);
        recyclerView.setAdapter(tankAdapter);
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

    boolean czolgPasuje (String nazwaCzolgu) {
        Tank tank = db.tankDao().findByName(nazwaCzolgu);
        if (roster.tiers.charAt(tank.tier % 10) == '1') {
            String nation = tank.nation;
            switch (nation) {
                case "gb": return czyKraj(0, tank);
                case "usa": return czyKraj(1, tank);
                case "german": return czyKraj(2, tank);
                case "zsrr": return czyKraj(3, tank);
                case "china": return czyKraj(4, tank);
                case "japan": return czyKraj(5, tank);
                case "france": return czyKraj(6, tank);
                case "italy": return czyKraj(7, tank);
                case "poland": return czyKraj(8, tank);
                case "sweden": return czyKraj(9, tank);
                case "czech": return czyKraj(10, tank);
                default: return false;
            }
        } else return false;
    }

    boolean czyKraj(int i, Tank tank) {
        if (roster.nation.charAt(i) == '1')
            return roster.official != 1 || tank.isOfficial != 0;
        else return false;
    }

    public void gotowe(View view) {
        Intent intent = new Intent(AddTanksActivity.this, RosterMainActivity.class);
        intent.putExtra("id", rosterID);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RosterMainActivity.class);
        intent.putExtra("id", rosterID);
        startActivity(intent);
    }
}