package com.mrdelicious.minitankswot.rules.terrains;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.CustomSpinner;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerrainsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EverythingDatabase db;
    ListView lvTerrains;
    ArrayAdapter<String> abilitiesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrains);
        this.setTitle("Teren i przeszkody");
        lvTerrains = findViewById(R.id.terrains_list);

        db = App.getDB(this);

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