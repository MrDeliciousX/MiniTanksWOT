package com.mrdelicious.minitankswot.rosters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.R;

public class RostersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosters);
        this.setTitle("Dywizje Pancerne");

        showRostersList();
    }

    public void showRostersList(){

    }

    public void createNewRoster(View view) {
        Intent intent = new Intent(this, CreateRosterFirstStepActivity.class);
        startActivity(intent);
    }
}