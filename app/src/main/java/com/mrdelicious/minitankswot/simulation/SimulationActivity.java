package com.mrdelicious.minitankswot.simulation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.CustomSpinner;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.MainActivity;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimulationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EverythingDatabase dbTanks;
    int def1 = 0;
    int def2 = 0;
    boolean hide1 = false;
    boolean hide2 = false;
    List<String> names = new ArrayList<>();
    AutoCompleteTextView name1, name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);
        this.setTitle("Symulacja 1vs1");

        dbTanks = App.getDB(this);

        names = dbTanks.tankDao().getAllNames();

        name1 = findViewById(R.id.sim_nameTank1);
        name2 = findViewById(R.id.sim_nameTank2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,names);
        name1.setAdapter(adapter);
        name2.setAdapter(adapter);

        fillFilters();

    }

    boolean advantage(){
        final boolean[] left = {false};
        ToggleButton toggle = findViewById(R.id.sim_advantageButton);
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> left[0] = isChecked);
        return left[0];
    }

    @SuppressLint("NonConstantResourceId")
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.sim_cover1:
                if (checked) def1 += 1;
                else def1 -= 1;
                break;
            case R.id.sim_cover2:
                if (checked) def2 += 1;
                else def2 -= 1;
                break;
            case R.id.sim_hide1:
                hide1 = checked;
                break;
            case R.id.sim_hide2:
                hide2 = checked;
                break;
            case R.id.sim_side1:
                if (checked)
                    def1 -= 1;
                else
                    def1 += 1;
                break;
            case R.id.sim_side2:
                if (checked)
                    def2 -= 1;
                else
                    def2 += 1;
                break;
            case R.id.sim_isClose:
                if (checked) {
                    def1 -= 1;
                    def2 -= 1;
                } else {
                    def1 += 1;
                    def2 += 1;
                }
                break;
        }
    }

    void fillFilters(){
        Spinner spinnerMove1;
        Spinner spinnerMove2;

        spinnerMove1 = findViewById(R.id.sim_move1);
        spinnerMove2 = findViewById(R.id.sim_move2);
        ArrayList<CustomSpinner> typesList = new ArrayList<>();
        typesList.add(new CustomSpinner(getString(R.string.stationary),0));
        typesList.add(new CustomSpinner("1",0));
        typesList.add(new CustomSpinner("2",0));
        typesList.add(new CustomSpinner("3",0));
        SpinnerAdapter spinnerAdapterTypes = new SpinnerAdapter(this,typesList);
        if (spinnerMove1 != null){
            spinnerMove1.setAdapter(spinnerAdapterTypes);
            spinnerMove1.setOnItemSelectedListener(this);
        }
        if (spinnerMove2 != null){
            spinnerMove2.setAdapter(spinnerAdapterTypes);
            spinnerMove2.setOnItemSelectedListener(this);
        }
    }

    public void compare(View view){
        String tank1 = String.valueOf(name1.getText());
        String tank2 = String.valueOf(name2.getText());

        Intent intent = new Intent(this, SimResultActivity.class);
        intent.putExtra("tank1", tank1);
        intent.putExtra("tank2", tank2);
        intent.putExtra("advantageRight", advantage());
        intent.putExtra("def1", def1);
        intent.putExtra("def2", def2);
        intent.putExtra("hide1", hide1);
        intent.putExtra("hide2", hide2);

        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}