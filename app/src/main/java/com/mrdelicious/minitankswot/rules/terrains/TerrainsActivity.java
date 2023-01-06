package com.mrdelicious.minitankswot.rules.terrains;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import com.mrdelicious.minitankswot.CustomSpinner;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.SpinnerAdapter;
import com.mrdelicious.minitankswot.rules.RulesDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerrainsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RulesDatabase db;
    ListView lvTerrains;
    ArrayAdapter<String> abilitiesArrayAdapter;
    String db_name = "db_rules.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrains);
        this.setTitle("Teren i przeszkody");
        lvTerrains = findViewById(R.id.terrains_list);

        db = Room.databaseBuilder(this, RulesDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

        showTerrainsOnListView(2);
        fillFilters();

        lvTerrains.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TerrainsActivity.this, TerrainProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }
    void showTerrainsOnListView(int official){
        List<String> findTerrains = new ArrayList<>();
        switch (official){
            case 0:
                findTerrains = db.terrainDao().findByOfficial(0);
                break;
            case 1:
                findTerrains = db.terrainDao().findByOfficial(1);
                break;
            case 2:
                findTerrains = db.terrainDao().getAllNames();
                break;
        }

        abilitiesArrayAdapter = new ArrayAdapter<>(
                TerrainsActivity.this,
                android.R.layout.simple_list_item_1,
                findTerrains.stream().sorted().collect(Collectors.toList())
        );
        lvTerrains.setAdapter(abilitiesArrayAdapter);
    }
    void fillFilters(){
        Spinner spinnerOfficials = findViewById(R.id.terrains_filter);
        ArrayList<CustomSpinner> list = new ArrayList<>();
        list.add(new CustomSpinner(getString(R.string.all),0));
        list.add(new CustomSpinner(getString(R.string.official),0));
        list.add(new CustomSpinner(getString(R.string.unofficials),0));
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this,list);
        if (spinnerOfficials != null){
            spinnerOfficials.setAdapter(spinnerAdapter);
            spinnerOfficials.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomSpinner items = (CustomSpinner)parent.getSelectedItem();
        String chosenFilter=items.getSpinnerText();
        switch (chosenFilter) {
            case "Wszystkie":
                showTerrainsOnListView(2);
                break;
            case "Oficjalne":
                showTerrainsOnListView(1);
                break;
            case "Nieoficjalne":
                showTerrainsOnListView(0);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}