package com.mrdelicious.minitankswot.rules.abilities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

public class AbilityProfileActivity extends AppCompatActivity {

    EverythingDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        db = App.getDB(this);

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