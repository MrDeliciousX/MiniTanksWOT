package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mrdelicious.minitankswot.rules.abilities.AbilitiesActivity;
import com.mrdelicious.minitankswot.cards.crits.CriticalHitActivity;
import com.mrdelicious.minitankswot.rules.terrains.TerrainsActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setTitle("Rozgrywka");
    }
    public void showCritical(View view) {
        Intent intent = new Intent(this, CriticalHitActivity.class);
        startActivity(intent);
    }
    public void showAbilities(View view) {
        Intent intent = new Intent(this, AbilitiesActivity.class);
        startActivity(intent);
    }
    public void showTerrains(View view) {
        Intent intent = new Intent(this, TerrainsActivity.class);
        startActivity(intent);
    }
}