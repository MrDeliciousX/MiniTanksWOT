package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.MainActivity;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.rosters.databaseStuff.Roster;

import java.util.List;

public class RostersActivity extends AppCompatActivity {

    EverythingDatabase db;
    ListView lvRosters;
    ArrayAdapter<String> rostersArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosters);
        this.setTitle("Plutony Pancerne");

        lvRosters = findViewById(R.id.rosters_rosterList);
        db = App.getDB(this);
        showRostersList();

        lvRosters.setOnItemClickListener((parent, view, position, id) -> {
            String name = String.valueOf(parent.getItemAtPosition(position));
            Roster roster = db.rosterDao().findByName(name);
            Intent intent = new Intent(RostersActivity.this, RosterMainActivity.class);
            intent.putExtra("id", roster.id);
            startActivity(intent);
        });
    }

    public void showRostersList(){
        List<String> rosters = db.rosterDao().getAllNames();

        rostersArrayAdapter = new ArrayAdapter<>(
                RostersActivity.this,
                android.R.layout.simple_list_item_1,
                rosters
        );
        lvRosters.setAdapter(rostersArrayAdapter);
    }

    public void createNewRoster(View view) {
        Intent intent = new Intent(this, RosterSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}