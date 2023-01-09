package com.mrdelicious.minitankswot.rosters;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class RosterMainActivity extends AppCompatActivity {

    EverythingDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_main);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        long id = clicked.getLong("id");
        Roster roster = db.rosterDao().findByID(id);

        this.setTitle(roster.name);

    }
}