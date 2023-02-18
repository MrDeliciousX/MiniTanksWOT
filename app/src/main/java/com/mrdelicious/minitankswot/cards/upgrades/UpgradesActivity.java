package com.mrdelicious.minitankswot.cards.upgrades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.GameActivity;
import com.mrdelicious.minitankswot.R;

public class UpgradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}