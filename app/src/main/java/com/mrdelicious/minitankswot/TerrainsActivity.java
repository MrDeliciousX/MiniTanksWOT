package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerrainsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper dbHelper;
    ListView lvTerrains;
    ArrayAdapter<String> abilitiesArrayAdapter;
    String db_name = "db_rules.db";
    String table = "terrains";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrains);
        this.setTitle("Teren i przeszkody");
        lvTerrains = findViewById(R.id.terrains_list);

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        showTerrainsOnListView(dbHelper,2);
        fillFilters();

        lvTerrains.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TerrainsActivity.this, TerrainProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }
    void showTerrainsOnListView(DatabaseHelper databaseHelper, int official){
        List<String> terrains = databaseHelper.getColumnFromDatabase(db_name,table,1, Cursor::getString);
        List<Integer> officials = databaseHelper.getColumnFromDatabase(db_name,table,3,Cursor::getInt);
        List<String> findAbilities = new ArrayList<>();
        for (int i = 0; i < terrains.size(); i++) {
            boolean good = true;
            if (official != 2)
                if (officials.get(i) != official)
                    good = false;
            if (good)
                findAbilities.add(terrains.get(i));
        }

        abilitiesArrayAdapter = new ArrayAdapter<>(
                TerrainsActivity.this,
                android.R.layout.simple_list_item_1,
                findAbilities.stream().sorted().collect(Collectors.toList())
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
                showTerrainsOnListView(dbHelper,2);
                break;
            case "Oficjalne":
                showTerrainsOnListView(dbHelper,1);
                break;
            case "Nieoficjalne":
                showTerrainsOnListView(dbHelper,0);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}