package com.mrdelicious.minitankswot.rules.abilities;

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

public class AbilitiesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RulesDatabase db;
    ListView lvAbilities;
    ArrayAdapter<String> abilitiesArrayAdapter;
    String db_name = "db_rules.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abilities);
        this.setTitle("Cechy pojazdów");
        lvAbilities = findViewById(R.id.abilities_list);

        db = Room.databaseBuilder(this, RulesDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

        showAbilitiesOnListView(2);
        fillFilters();

        lvAbilities.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(AbilitiesActivity.this, AbilityProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }
    void showAbilitiesOnListView(int official) {
        List<String> findAbilities = new ArrayList<>();
        switch (official){
            case 0:
                findAbilities = db.abilityDao().findByOfficial(0);
                break;
            case 1:
                findAbilities = db.abilityDao().findByOfficial(1);
                break;
            case 2:
                findAbilities = db.abilityDao().getAllNames();
                break;
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
                showAbilitiesOnListView(2);
                break;
            case "Oficjalne":
                showAbilitiesOnListView(1);
                break;
            case "Nieoficjalne":
                showAbilitiesOnListView(0);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}