package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class CreateRosterFirstStepActivity extends AppCompatActivity {

    EverythingDatabase db;
    private static final String TAG = "CreateRosterFirstStepActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_roster_first_step);
        this.setTitle("Nowy pluton");

        db = App.getDB(this);
    }

    public void nextStep(View view) {
        Intent intent = new Intent(this, RosterMainActivity.class);
        EditText editText = findViewById(R.id.CreateRosterFirstStep_name);
        String name = editText.getText().toString();
        editText = findViewById(R.id.CreateRosterFirstStep_ptsLimit);
        String pts = editText.getText().toString();
        if (!name.equals("")) {

            if (db.rosterDao().findByName(name) == null) {

                if (!pts.equals("") && Integer.parseInt(pts) >= 0) {
                    Roster roster = new Roster();
                    roster.name = name;
                    roster.limitPts = Integer.parseInt(pts);
                    roster.currentPts = 0;
                    roster.tiers = "1111111111";
                    roster.official = 0;
                    long id = db.rosterDao().insertNew(roster);
                    Log.i(TAG, "roster: " + roster);
                    Log.i(TAG, String.valueOf(id));
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Błąd limitu punktów", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Nazwa już użyta", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Brak nazwy", Toast.LENGTH_SHORT).show();
    }
}