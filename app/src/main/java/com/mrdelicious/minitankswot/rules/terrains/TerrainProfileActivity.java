package com.mrdelicious.minitankswot.rules.terrains;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class TerrainProfileActivity extends AppCompatActivity {

    EverythingDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        db = App.getDB(this);

        TextView title = findViewById(R.id.terrainProfile_name);
        title.setText(name);

        TextView official = findViewById(R.id.terrainProfile_official);
        TextView text = findViewById(R.id.terrainProfile_text);

        Terrain terrain = db.terrainDao().findByName(name);
        text.setText(terrain.text);

        if (terrain.official == 1)
            official.setVisibility(View.INVISIBLE);
    }
}