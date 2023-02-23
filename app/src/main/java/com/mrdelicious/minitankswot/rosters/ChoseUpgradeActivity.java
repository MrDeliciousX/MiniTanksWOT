package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class ChoseUpgradeActivity extends AppCompatActivity {
    String name, type;
    EverythingDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_upgrade);

        Bundle chosen = getIntent().getExtras();
        name = chosen.getString("nazwa");
        type = chosen.getString("typ");

        db = App.getDB(this);
        this.setTitle(name);


    }
}