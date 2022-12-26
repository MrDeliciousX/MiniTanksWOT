package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showTankList(View view) {
        Intent intent = new Intent(this,TanksActivity.class);
        startActivity(intent);
    }
    public void showRules(View view) {
        Intent intent = new Intent(this,RulesActivity.class);
        startActivity(intent);
    }
    public void openSimulation(View view) {
        Intent intent = new Intent(this,SimulationActivity.class);
        startActivity(intent);
    }
    public void showRosters(View view) {
        Intent intent = new Intent(this,RostersActivity.class);
        startActivity(intent);
    }
}