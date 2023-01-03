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
import java.util.stream.Collectors;

public class TanksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper dbHelper;
    ListView lvTanks;
    ArrayAdapter<String> tankArrayAdapter;
    Spinner spinnerTypes;
    Spinner spinnerNations;
    Spinner spinnerTiers;
    Spinner spinnerOfficials;
    String db_name = "db_tanks.db";
    String table = "TANKS_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);
        lvTanks = findViewById(R.id.tanks_tankList);
        this.setTitle("Lista Czołgów");

        fillFilters();

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showTanksOnListView(dbHelper);

        lvTanks.setOnItemClickListener((parent, view, position, id) -> {
            String tank = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TanksActivity.this, TankProfileActivity.class);
            intent.putExtra("name", tank);
            startActivity(intent);
        });
    }
    void showTanksOnListView(DatabaseHelper databaseHelper) {
        tankArrayAdapter = new ArrayAdapter<>(
                TanksActivity.this,
                android.R.layout.simple_list_item_1,
                databaseHelper.getColumnFromDatabase(db_name, table,1, Cursor::getString).stream().sorted().collect(Collectors.toList())
        );
        lvTanks.setAdapter(tankArrayAdapter);
    }
    void fillFilters(){
        spinnerTypes = findViewById(R.id.tanks_filterType);
        ArrayList<CustomSpinner> typesList = new ArrayList<>();
        typesList.add(new CustomSpinner(getString(R.string.type),0));
        typesList.add(new CustomSpinner(getString(R.string.light),R.drawable.ic_android_black_24dp));
        typesList.add(new CustomSpinner(getString(R.string.medium),R.drawable.ic_android_black_24dp));
        typesList.add(new CustomSpinner(getString(R.string.heavy),R.drawable.ic_android_black_24dp));
        typesList.add(new CustomSpinner(getString(R.string.destroyer),R.drawable.ic_android_black_24dp));
        typesList.add(new CustomSpinner(getString(R.string.artillery),R.drawable.ic_android_black_24dp));
        SpinnerAdapter spinnerAdapterTypes = new SpinnerAdapter(this,typesList);
        if (spinnerTypes != null){
             spinnerTypes.setAdapter(spinnerAdapterTypes);
             spinnerTypes.setOnItemSelectedListener(this);
        }

        spinnerNations = findViewById(R.id.tanks_filterNation);
        ArrayList<CustomSpinner> nationsList = new ArrayList<>();
        nationsList.add(new CustomSpinner(getString(R.string.nation),0));
        nationsList.add(new CustomSpinner(getString(R.string.thirdReich),R.drawable.ic_android_black_24dp));
        nationsList.add(new CustomSpinner(getString(R.string.usa),R.drawable.ic_android_black_24dp));
        nationsList.add(new CustomSpinner(getString(R.string.cccp),R.drawable.ic_android_black_24dp));
        nationsList.add(new CustomSpinner(getString(R.string.greatBritain),R.drawable.ic_android_black_24dp));
        SpinnerAdapter spinnerAdapterNations = new SpinnerAdapter(this,nationsList);
        if (spinnerNations != null){
            spinnerNations.setAdapter(spinnerAdapterNations);
            spinnerNations.setOnItemSelectedListener(this);
        }

        spinnerTiers = findViewById(R.id.tanks_filterTier);
        ArrayList<CustomSpinner> tierList = new ArrayList<>();
        tierList.add(new CustomSpinner("Tier",0));
        tierList.add(new CustomSpinner("I",0));
        tierList.add(new CustomSpinner("II",0));
        tierList.add(new CustomSpinner("III",0));
        tierList.add(new CustomSpinner("IV",0));
        tierList.add(new CustomSpinner("V",0));
        tierList.add(new CustomSpinner("VI",0));
        tierList.add(new CustomSpinner("VII",0));
        tierList.add(new CustomSpinner("VIII",0));
        tierList.add(new CustomSpinner("IX",0));
        tierList.add(new CustomSpinner("X",0));
        SpinnerAdapter spinnerAdapterTiers = new SpinnerAdapter(this,tierList);
        if (spinnerTiers != null){
            spinnerTiers.setAdapter(spinnerAdapterTiers);
            spinnerTiers.setOnItemSelectedListener(this);
        }

        spinnerOfficials = findViewById(R.id.tanks_filterOfficial);
        ArrayList<CustomSpinner> officialList = new ArrayList<>();
        officialList.add(new CustomSpinner(getString(R.string.all),0));
        officialList.add(new CustomSpinner(getString(R.string.official),0));
        officialList.add(new CustomSpinner(getString(R.string.unofficials),0));
        SpinnerAdapter spinnerAdapterOfficial = new SpinnerAdapter(this,officialList);
        if (spinnerOfficials != null){
            spinnerOfficials.setAdapter(spinnerAdapterOfficial);
            spinnerOfficials.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomSpinner items = (CustomSpinner)parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}