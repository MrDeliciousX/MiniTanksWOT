package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);
        this.setTitle("Lista Czołgów");
    }
}