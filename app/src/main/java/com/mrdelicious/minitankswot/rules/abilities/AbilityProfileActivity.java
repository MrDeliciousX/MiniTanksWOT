package com.mrdelicious.minitankswot.rules.abilities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.rules.RulesDatabase;

public class AbilityProfileActivity extends AppCompatActivity {

    RulesDatabase db;
    String db_name = "db_rules.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        db = Room.databaseBuilder(this, RulesDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

        TextView title = findViewById(R.id.abilityProfile_name);
        title.setText(name);

        TextView official = findViewById(R.id.abilityProfile_official);
        TextView text = findViewById(R.id.abilityProfile_text);

        Ability ability = db.abilityDao().findByName(name);
        text.setText(ability.text);

        if (ability.isOfficial == 1)
            official.setVisibility(View.INVISIBLE);
    }
}