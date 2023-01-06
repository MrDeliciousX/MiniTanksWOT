package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String dontShowAgainKey = "com.mrdelicious.minitankswot.dontShowAgainKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView appVersion = findViewById(R.id.main_version);
        appVersion.setText(R.string.appVersion);
    }
    public void showTankList(View view) {
        Intent intent = new Intent(this,TanksActivity.class);
        startActivity(intent);
    }
    public void showGame(View view) {
        Intent intent = new Intent(this,GameActivity.class);
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
