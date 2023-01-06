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

public class AbilitiesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper dbHelper;
    ListView lvAbilities;
    ArrayAdapter<String> abilitiesArrayAdapter;
    String db_name = "db_rules.db";
    String table = "abilities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities);
        this.setTitle("Cechy pojazdów");
        lvAbilities = findViewById(R.id.abilities_list);

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        showAbilitiesOnListView(dbHelper,2);
        fillFilters();

        lvAbilities.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(AbilitiesActivity.this, AbilityProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }
    void showAbilitiesOnListView(DatabaseHelper databaseHelper, int official) {
        List<String> abilities = databaseHelper.getColumnFromDatabase(db_name,table,1, Cursor::getString);
        List<Integer> officials = databaseHelper.getColumnFromDatabase(db_name,table,3,Cursor::getInt);
        List<String> findAbilities = new ArrayList<>();
        for (int i = 0; i < abilities.size(); i++) {
            boolean good = true;
            if (official != 2)
                if (officials.get(i) != official)
                    good = false;
            if (good)
                findAbilities.add(abilities.get(i));
        }

        abilitiesArrayAdapter = new ArrayAdapter<>(
                AbilitiesActivity.this,
                android.R.layout.simple_list_item_1,
                findAbilities.stream().sorted().collect(Collectors.toList())
        );
        lvAbilities.setAdapter(abilitiesArrayAdapter);
    }
    void fillFilters(){
        Spinner spinnerOfficials = findViewById(R.id.abilities_filter);
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
                showAbilitiesOnListView(dbHelper,2);
                break;
            case "Oficjalne":
                showAbilitiesOnListView(dbHelper,1);
                break;
            case "Nieoficjalne":
                showAbilitiesOnListView(dbHelper,0);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}