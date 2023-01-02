package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class RostersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rosters);
        this.setTitle("Dywizje Pancerne");
    }
}