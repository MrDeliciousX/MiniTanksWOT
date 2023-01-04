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

public class TanksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper dbHelper;
    ListView lvTanks;
    ArrayAdapter<String> tankArrayAdapter;
    String db_name = "db_tanks.db";
    String table = "TANKS_TABLE";
    String nation = "";
    String type = "";
    int tier = 0;
    int official = 2;

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
        showTanksOnListView(dbHelper,"",0,"",2);

        lvTanks.setOnItemClickListener((parent, view, position, id) -> {
            String tank = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TanksActivity.this, TankProfileActivity.class);
            intent.putExtra("name", tank);
            startActivity(intent);
        });
    }
    void showTanksOnListView(DatabaseHelper databaseHelper,String nation,int tier,String type,int official) {
        List<String> tanks = databaseHelper.getColumnFromDatabase(db_name, table,1, Cursor::getString);
        List<String> nations = databaseHelper.getColumnFromDatabase(db_name,table,14,Cursor::getString);
        List<Integer> tiers = databaseHelper.getColumnFromDatabase(db_name,table,11,Cursor::getInt);
        List<Integer> officials = databaseHelper.getColumnFromDatabase(db_name,table,3,Cursor::getInt);
        List<String> types = databaseHelper.getColumnFromDatabase(db_name,table,12,Cursor::getString);
        List<String> findTanks = new ArrayList<>();

        for (int i = 0; i < tanks.size(); i++) {
            boolean good = true;
            if (!nation.equals(""))
                if (!nation.equals(nations.get(i)))
                    good = false;
            if (tier != 0)
                if (tier != tiers.get(i))
                    good = false;
            if (!type.equals(""))
                if (!type.equals(types.get(i)))
                    good = false;
            if (official != 2)
                if (official != officials.get(i))
                    good = false;
            if (good)
                findTanks.add(tanks.get(i));
        }

        tankArrayAdapter = new ArrayAdapter<>(
                TanksActivity.this,
                android.R.layout.simple_list_item_1,
                findTanks.stream().sorted().collect(Collectors.toList())
        );
        lvTanks.setAdapter(tankArrayAdapter);
    }
    void fillFilters(){
        Spinner spinnerTypes;
        Spinner spinnerNations;
        Spinner spinnerTiers;
        Spinner spinnerOfficials;

        spinnerTypes = findViewById(R.id.tanks_filterType);
        ArrayList<CustomSpinner> typesList = new ArrayList<>();
        typesList.add(new CustomSpinner(getString(R.string.type),0));
        typesList.add(new CustomSpinner(getString(R.string.light),R.drawable.type_light));
        typesList.add(new CustomSpinner(getString(R.string.medium),R.drawable.type_medium));
        typesList.add(new CustomSpinner(getString(R.string.heavy),R.drawable.type_heavy));
        typesList.add(new CustomSpinner(getString(R.string.destroyer),R.drawable.type_destroyer));
        typesList.add(new CustomSpinner(getString(R.string.artillery),R.drawable.type_spg));
        SpinnerAdapter spinnerAdapterTypes = new SpinnerAdapter(this,typesList);
        if (spinnerTypes != null){
             spinnerTypes.setAdapter(spinnerAdapterTypes);
             spinnerTypes.setOnItemSelectedListener(this);
        }

        spinnerNations = findViewById(R.id.tanks_filterNation);
        ArrayList<CustomSpinner> nationsList = new ArrayList<>();
        nationsList.add(new CustomSpinner(getString(R.string.nation),0));
        nationsList.add(new CustomSpinner(getString(R.string.thirdReich),R.drawable.flag_german));
        nationsList.add(new CustomSpinner(getString(R.string.usa),R.drawable.flag_usa));
        nationsList.add(new CustomSpinner(getString(R.string.cccp),R.drawable.flag_zsrr));
        nationsList.add(new CustomSpinner(getString(R.string.greatBritain),R.drawable.flag_gb));
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
    public void searchTanks(View view){
        showTanksOnListView(dbHelper,nation,tier,type,official);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomSpinner items = (CustomSpinner)parent.getSelectedItem();
        String chosenFilter=items.getSpinnerText();
        switch (chosenFilter) {
            case "Typ":
                type = "";
                break;
            case "Lekki":
                type = "light";
                break;
            case "Średni":
                type = "medium";
                break;
            case "Ciężki":
                type = "heavy";
                break;
            case "Niszczyciel":
                type = "destroyer";
                break;
            case "Artyleria":
                type = "spg";
                break;
            case "Kraj":
                nation = "";
                break;
            case "III Rzesza":
                nation = "german";
                break;
            case "USA":
                nation = "usa";
                break;
            case "ZSRR":
                nation = "zsrr";
                break;
            case "Wielka Brytania":
                nation = "gb";
                break;
            case "Tier":
                tier = 0;
                break;
            case "I":
                tier = 1;
                break;
            case "II":
                tier = 2;
                break;
            case "III":
                tier = 3;
                break;
            case "IV":
                tier = 4;
                break;
            case "V":
                tier = 5;
                break;
            case "VI":
                tier = 6;
                break;
            case "VII":
                tier = 7;
                break;
            case "VIII":
                tier = 8;
                break;
            case "IX":
                tier = 9;
                break;
            case "X":
                tier = 10;
                break;
            case "Wszystkie":
                official = 2;
                break;
            case "Oficjalne":
                official = 1;
                break;
            case "Nieoficjalne":
                official = 0;
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}