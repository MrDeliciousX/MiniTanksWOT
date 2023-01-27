package com.mrdelicious.minitankswot.rosters;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddTanksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EverythingDatabase db;
    Long rosterID;
    ListView tankList;
    String type;
    List<String> findTanks, nations;
    List<Integer> costs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tanks);

        tankList = findViewById(R.id.addTanks_tankList);
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
        ArrayList<TankOnList> tanks = new ArrayList<>();
        for (int i = 0; i < findTanks.size(); i++) {
            TankOnList tank = new TankOnList(findTanks.get(i), costs.get(i), imageFill(nations.get(i)));
            tanks.add(tank);
        }
        Collections.sort(tanks);

        TankAdapter tankAdapter = new TankAdapter(AddTanksActivity.this, tanks, rosterID);
        tankList.setAdapter(tankAdapter);
    }

    int imageFill(String nation){
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("flag_");
        for (int i = 0; i < nation.length(); i++) {
            nameHelper.append(nation.charAt(i));
        }
        return getResources().getIdentifier(nameHelper.toString(), "drawable", getPackageName());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}