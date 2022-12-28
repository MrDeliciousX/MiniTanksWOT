package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

public class CriticalActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ListView lvCrits;
    ArrayAdapter<String> critArrayAdapter;
    String db_name = "db_crit_cards.db";
    String table = "CRITS_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critical);
        this.setTitle("Uszkodzenia krytyczne");

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowCritsOnListView(dbHelper);
    }
    public void ShowCritsOnListView(DatabaseHelper databaseHelper) {
        critArrayAdapter = new ArrayAdapter<>(CriticalActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getAllFromDatabase(db_name, table));
        lvCrits.setAdapter(critArrayAdapter);
    }
}