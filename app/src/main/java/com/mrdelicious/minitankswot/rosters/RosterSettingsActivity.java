package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class RosterSettingsActivity extends AppCompatActivity {

    long rosterID;
    EverythingDatabase db;
    Roster roster;
    EditText name, pts;
    CheckBox t1, t2, t3, t4, t5, t6, t7, t8, t9, t0;
    ImageButton gb, usa, ger, zsrr, chi, jap, fr, ital, pl, swed, czech;
    ToggleButton official;
    int[] nations = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    String aktualnaNazwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_settings);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        rosterID = clicked.getLong("id");
        roster = db.rosterDao().findByID(rosterID);

        this.setTitle("Ustawienia plutonu");
        przypisanie();
        stanUstawien();

    }

    void stanUstawien() {
        name.setText(roster.name);
        aktualnaNazwa = roster.name;
        pts.setText(String.valueOf(roster.limitPts));
        official.setChecked(roster.official == 1);
        t1.setChecked(Character.getNumericValue(roster.tiers.charAt(1)) == 1);
        t2.setChecked(Character.getNumericValue(roster.tiers.charAt(2)) == 1);
        t3.setChecked(Character.getNumericValue(roster.tiers.charAt(3)) == 1);
        t4.setChecked(Character.getNumericValue(roster.tiers.charAt(4)) == 1);
        t5.setChecked(Character.getNumericValue(roster.tiers.charAt(5)) == 1);
        t6.setChecked(Character.getNumericValue(roster.tiers.charAt(6)) == 1);
        t7.setChecked(Character.getNumericValue(roster.tiers.charAt(7)) == 1);
        t8.setChecked(Character.getNumericValue(roster.tiers.charAt(8)) == 1);
        t9.setChecked(Character.getNumericValue(roster.tiers.charAt(9)) == 1);
        t0.setChecked(Character.getNumericValue(roster.tiers.charAt(0)) == 1);
        if (roster.nation.charAt(0) == '1') gb.setBackgroundResource(R.color.teal_200);
        else gb.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(1) == '1') usa.setBackgroundResource(R.color.teal_200);
        else usa.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(2) == '1') ger.setBackgroundResource(R.color.teal_200);
        else ger.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(3) == '1') zsrr.setBackgroundResource(R.color.teal_200);
        else zsrr.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(4) == '1') chi.setBackgroundResource(R.color.teal_200);
        else chi.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(5) == '1') jap.setBackgroundResource(R.color.teal_200);
        else jap.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(6) == '1') fr.setBackgroundResource(R.color.teal_200);
        else fr.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(7) == '1') ital.setBackgroundResource(R.color.teal_200);
        else ital.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(8) == '1') pl.setBackgroundResource(R.color.teal_200);
        else pl.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(9) == '1') swed.setBackgroundResource(R.color.teal_200);
        else swed.setBackgroundResource(R.color.gray_2);
        if (roster.nation.charAt(10) == '1') czech.setBackgroundResource(R.color.teal_200);
        else czech.setBackgroundResource(R.color.gray_2);

        for (int i = 0; i < 11; i++) {
            nations[i] = Character.getNumericValue(roster.nation.charAt(i));
        }
    }

    void przypisanie() {
        name = findViewById(R.id.rosSet_name);
        pts = findViewById(R.id.rosSet_limitPts);
        t1 = findViewById(R.id.rosSet_t1);
        t2 = findViewById(R.id.rosSet_t2);
        t3 = findViewById(R.id.rosSet_t3);
        t4 = findViewById(R.id.rosSet_t4);
        t5 = findViewById(R.id.rosSet_t5);
        t6 = findViewById(R.id.rosSet_t6);
        t7 = findViewById(R.id.rosSet_t7);
        t8 = findViewById(R.id.rosSet_t8);
        t9 = findViewById(R.id.rosSet_t9);
        t0 = findViewById(R.id.rosSet_t0);
        gb = findViewById(R.id.rosSet_gb);
        usa = findViewById(R.id.rosSet_usa);
        ger = findViewById(R.id.rosSet_german);
        zsrr = findViewById(R.id.rosSet_zsrr);
        chi = findViewById(R.id.rosSet_china);
        jap = findViewById(R.id.rosSet_jap);
        fr = findViewById(R.id.rosSet_fr);
        ital = findViewById(R.id.rosSet_italy);
        pl = findViewById(R.id.rosSet_pl);
        swed = findViewById(R.id.rosSet_sweden);
        czech = findViewById(R.id.rosSet_czech);
        official = findViewById(R.id.rosSet_officialBtn);
    }

    public void saveAndBack(View view) {
        if (!name.getText().toString().equals("")) {
            if (db.rosterDao().findByName(name.getText().toString()) == null || name.getText().toString().equals(aktualnaNazwa)) {
                if (!pts.getText().toString().equals("") && Integer.parseInt(pts.getText().toString()) >= 0) {
                    aktualizacjaWartosci();
                    Intent intent = new Intent(RosterSettingsActivity.this, RosterMainActivity.class);
                    intent.putExtra("id", rosterID);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Błąd limitu punktów", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Nazwa już użyta", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Brak nazwy", Toast.LENGTH_SHORT).show();

    }

    void aktualizacjaWartosci() {
        db.rosterDao().zmianaNazwy(name.getText().toString(), rosterID);
        db.rosterDao().zmianaLimituPkt(Integer.parseInt(pts.getText().toString()), rosterID);

        if (official.isChecked()) db.rosterDao().zmianaCzyOficjalny(1, rosterID);
        else db.rosterDao().zmianaCzyOficjalny(0, rosterID);

        CheckBox[] tiery = {t0, t1, t2, t3, t4, t5, t6, t7, t8, t9};
        StringBuilder noweTiery = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (tiery[i].isChecked()) noweTiery.append(1);
            else noweTiery.append(0);
        }
        db.rosterDao().zmianaTierow(noweTiery.toString(), rosterID);

        StringBuilder noweKraje = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            noweKraje.append(nations[i]);
        }
        db.rosterDao().zmianaKrajow(noweKraje.toString(), rosterID);
    }

    public void changeGb (View view) {
        int i = changeNation(0);
        if (i == 1) gb.setBackgroundResource(R.color.teal_200);
        else gb.setBackgroundResource(R.color.gray_2);
    }

    public void changeUsa (View view) {
        int i = changeNation(1);
        if (i == 1) usa.setBackgroundResource(R.color.teal_200);
        else usa.setBackgroundResource(R.color.gray_2);
    }

    public void changeGer (View view) {
        int i = changeNation(2);
        if (i == 1) ger.setBackgroundResource(R.color.teal_200);
        else ger.setBackgroundResource(R.color.gray_2);
    }

    public void changeZsrr (View view) {
        int i = changeNation(3);
        if (i == 1) zsrr.setBackgroundResource(R.color.teal_200);
        else zsrr.setBackgroundResource(R.color.gray_2);
    }

    public void changeChi (View view) {
        int i = changeNation(4);
        if (i == 1) chi.setBackgroundResource(R.color.teal_200);
        else chi.setBackgroundResource(R.color.gray_2);
    }

    public void changeJap (View view) {
        int i = changeNation(5);
        if (i == 1) jap.setBackgroundResource(R.color.teal_200);
        else jap.setBackgroundResource(R.color.gray_2);
    }

    public void changeFr (View view) {
        int i = changeNation(6);
        if (i == 1) fr.setBackgroundResource(R.color.teal_200);
        else fr.setBackgroundResource(R.color.gray_2);
    }

    public void changeItal (View view) {
        int i = changeNation(7);
        if (i == 1) ital.setBackgroundResource(R.color.teal_200);
        else ital.setBackgroundResource(R.color.gray_2);
    }

    public void changePl (View view) {
        int i = changeNation(8);
        if (i == 1) pl.setBackgroundResource(R.color.teal_200);
        else pl.setBackgroundResource(R.color.gray_2);
    }

    public void changeSwed (View view) {
        int i = changeNation(9);
        if (i == 1) swed.setBackgroundResource(R.color.teal_200);
        else swed.setBackgroundResource(R.color.gray_2);
    }

    public void changeCzech (View view) {
        int i = changeNation(10);
        if (i == 1) czech.setBackgroundResource(R.color.teal_200);
        else czech.setBackgroundResource(R.color.gray_2);
    }

    int changeNation(int kraj) {
        if (nations[kraj] == 1)
            nations[kraj] = 0;
        else
            nations[kraj] = 1;
        return nations[kraj];
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RosterMainActivity.class);
        intent.putExtra("id", rosterID);
        startActivity(intent);
    }
}