package com.mrdelicious.minitankswot.rosters;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import java.util.ArrayList;
import java.util.List;

public class AddTanksActivity extends AppCompatActivity {

    EverythingDatabase db;
    Long rosterID;
    ListView tankList;
    String type;
    List<String> findTanks;
    List<Integer> costs;
    List<String> nations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tanks);

        tankList.findViewById(R.id.addTanks_list);
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
            TankOnList tank = new TankOnList(findTanks.get(i),costs.get(i),nations.get(i));
            tanks.add(tank);
        }

        TankAdapter tankAdapter = new TankAdapter(AddTanksActivity.this, tanks);
        tankList.setAdapter(tankAdapter);
    }


}