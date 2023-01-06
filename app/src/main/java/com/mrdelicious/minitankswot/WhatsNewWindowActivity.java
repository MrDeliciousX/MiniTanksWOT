package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class WhatsNewWindowActivity extends AppCompatActivity {

    boolean dontShowAgain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_new_window);
    }
    public void close(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void dontShowAgain(View view){
        dontShowAgain = ((CheckBox) view).isChecked();
    }
}