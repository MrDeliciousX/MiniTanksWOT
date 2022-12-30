package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.stream.Collectors;

public class TanksActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ListView lvTanks;
    ArrayAdapter<String> tankArrayAdapter;
    String db_name = "db_tanks.db";
    String table = "TANKS_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);
        lvTanks = findViewById(R.id.tanks_tankList);
        this.setTitle("Lista Czołgów");

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowTanksOnListView(dbHelper);

        lvTanks.setOnItemClickListener((parent, view, position, id) -> {
            String tank = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TanksActivity.this, TankProfileActivity.class);
            intent.putExtra("name", tank);
            startActivity(intent);
        });
    }
    public void ShowTanksOnListView(DatabaseHelper databaseHelper) {
        tankArrayAdapter = new ArrayAdapter<>(
                TanksActivity.this,
                android.R.layout.simple_list_item_1,
                databaseHelper.getColumnFromDatabase(db_name, table,1, Cursor::getString).stream().sorted().collect(Collectors.toList())
        );
        lvTanks.setAdapter(tankArrayAdapter);
    }
}