package com.mrdelicious.minitankswot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class WhatsNewWindowActivity extends AppCompatActivity {

    boolean dontShowAgain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_new_window);
        this.setTitle("Nowości i zmiany");
    }
    public void close(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void dontShowAgain(View view){
        dontShowAgain = ((CheckBox) view).isChecked();
    }
}