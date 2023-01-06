package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
}