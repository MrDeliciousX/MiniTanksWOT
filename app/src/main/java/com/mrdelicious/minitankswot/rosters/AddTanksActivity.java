package com.mrdelicious.minitankswot.rosters;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AddTanksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EverythingDatabase db;
    Long rosterID;
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
            tank = new TankOnList(findTanks.get(i), flagFill(nations.get(i)), costs.get(i), tankImageFill(findTanks.get(i)));
            tankOnList.add(tank);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}