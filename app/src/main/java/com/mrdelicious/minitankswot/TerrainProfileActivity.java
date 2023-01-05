package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;

public class TerrainProfileActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String db_name = "db_rules.db";
    String table = "TERRAIN_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        TextView title = findViewById(R.id.terrainProfile_name);
        title.setText(name);

        TextView official = findViewById(R.id.terrainProfile_official);
        TextView text = findViewById(R.id.terrainProfile_text);

        String[] ability = getTerrainContent(name);
        text.setText(ability[1]);

        if (ability[2].equals("1"))
            official.setVisibility(View.INVISIBLE);
    }
    String[] getTerrainContent(String name){
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] content = new String[3];
        List<String> names = dbHelper.getColumnFromDatabase(db_name,table,1, Cursor::getString);
        List<String> texts = dbHelper.getColumnFromDatabase(db_name,table,2,Cursor::getString);
        List<String> officials = dbHelper.getColumnFromDatabase(db_name,table,3,Cursor::getString);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)){
                content[0] = name;
                content[1] = texts.get(i);
                content[2] = officials.get(i);
            }
        }
        return content;
    }
}