package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class RosterSettingsActivity extends AppCompatActivity {

    long rosterID;
    EverythingDatabase db;
    EditText name, pts;
    CheckBox t1, t2, t3, t4, t5, t6, t7, t8, t9, t0;
    ImageButton gb, usa, ger, zsrr, chi, jap, fr, ital, pl, swed, czech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_settings);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        rosterID = clicked.getLong("id");
        Roster roster = db.rosterDao().findByID(rosterID);

        this.setTitle("Ustawienia plutonu");
        przypisanie();

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
    }
}