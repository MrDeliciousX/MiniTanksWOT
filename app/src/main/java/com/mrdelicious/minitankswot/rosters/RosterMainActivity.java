package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class RosterMainActivity extends AppCompatActivity {

    EverythingDatabase db;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_main);

        db = App.getDB(this);

        Bundle clicked = getIntent().getExtras();
        id = clicked.getLong("id");
        Roster roster = db.rosterDao().findByID(id);

        this.setTitle(roster.name);

    }

    public void addLTs(View view) {
        openAddTanks("light");
    }

    public void addMTs(View view) {
        openAddTanks("medium");
    }

    public void addHTs(View view) {
        openAddTanks("heavy");
    }

    public void addTDs(View view) {
        openAddTanks("destroyer");
    }

    public void addSPGs(View view) {
        openAddTanks("spg");
    }

    void openAddTanks(String type) {
        Intent intent = new Intent(RosterMainActivity.this, AddTanksActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("rosterID", id);
        startActivity(intent);
    }
}