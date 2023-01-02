package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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
        lvCrits = findViewById(R.id.critical_listCrits);
        this.setTitle("Uszkodzenia krytyczne");

        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowCritsOnListView(dbHelper);

        lvCrits.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(CriticalActivity.this, RandomCritActivity.class);
            intent.putExtra("index",position);
            startActivity(intent);
        });
    }
    void ShowCritsOnListView(DatabaseHelper databaseHelper) {
        critArrayAdapter = new ArrayAdapter<>(
                CriticalActivity.this,
                android.R.layout.simple_list_item_1,
                databaseHelper.getColumnFromDatabase(db_name, table,1, Cursor::getString)
        );
        lvCrits.setAdapter(critArrayAdapter);
    }
    public void ShowRandomCrit(View view) {
        Intent intent = new Intent(this,RandomCritActivity.class);
        startActivity(intent);
    }
}