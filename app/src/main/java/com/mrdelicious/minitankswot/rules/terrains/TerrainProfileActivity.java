package com.mrdelicious.minitankswot.rules.terrains;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.rules.RulesDatabase;

public class TerrainProfileActivity extends AppCompatActivity {

    RulesDatabase db;
    String db_name = "db_rules.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terrain_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        db = Room.databaseBuilder(this, RulesDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

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