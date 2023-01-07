package com.mrdelicious.minitankswot.tanks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.mrdelicious.minitankswot.rules.abilities.AbilityProfileActivity;
import com.mrdelicious.minitankswot.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TankProfileActivity extends AppCompatActivity {

    TankDatabase db;
    String db_name = "db_tanks.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_profile);

        db = Room.databaseBuilder(this, TankDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        Tank tank = db.tankDao().findByName(name);
        ImageView image = findViewById(R.id.tankProfile_image);
        imageFill(name,image);
        statisticFill(tank);
        showAbilitiesList(tank);

        ListView lvAbilities = findViewById(R.id.tankProfile_listRules);
        lvAbilities.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TankProfileActivity.this, AbilityProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }

    List<String> textToList(String text){
        List<String> lista = new ArrayList<>();
        StringBuilder helper = new StringBuilder();

        if (text != null) {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) != ' ') {
                    if (text.charAt(i) == '_') helper.append(' ');
                    else helper.append(text.charAt(i));
                } else {
                    lista.add(helper.toString());
                    helper = new StringBuilder();
                }
            }
            lista.add(helper.toString());
        }

        return lista;
    }

    void statisticFill(Tank tank){
        TextView textView = findViewById(R.id.tankProfile_tankName);
        textView.setText(tank.tankName);
        textView = findViewById(R.id.tankProfile_firepowerV);
        textView.setText(String.valueOf(tank.firepower));
        textView = findViewById(R.id.tankProfile_defV);
        textView.setText(String.valueOf(tank.survivability));
        textView = findViewById(R.id.tankProfile_moveV);
        textView.setText(String.valueOf(tank.mobility));
        textView = findViewById(R.id.tankProfile_initiativeV);
        textView.setText(String.valueOf(tank.initiative));
        textView = findViewById(R.id.tankProfile_costV);
        textView.setText(String.valueOf(tank.tankCost));
        textView = findViewById(R.id.tankProfile_HPV);
        textView.setText(String.valueOf(tank.hp));
        textView = findViewById(R.id.tankProfile_history);
        textView.setText(tank.history);
        textView = findViewById(R.id.tankProfile_typeV);
        textView.setText(tank.type);
        textView = findViewById(R.id.tankProfile_nationV);
        textView.setText(tank.nation);
        textView = findViewById(R.id.tankProfile_tierV);
        textView.setText(String.valueOf(tank.tier));

        textView = findViewById(R.id.tankProfile_official);
        if (tank.isOfficial == 1) textView.setVisibility(View.INVISIBLE);
    }

    void imageFill(String name, ImageView image){
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("tank_");
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i)==' ' || name.charAt(i)=='-' || name.charAt(i)=='(' || name.charAt(i)==')' || name.charAt(i)=='.'){
                nameHelper.append('_');
            } else nameHelper.append(name.charAt(i));
        }
        nameHelper = new StringBuilder(nameHelper.toString().toLowerCase(Locale.ROOT));
        int imageHelper = getResources().getIdentifier(nameHelper.toString(), "drawable", getPackageName());
        image.setImageResource(imageHelper);
    }

    public void showAbilitiesList(Tank tank){
        ListView tankSkills = findViewById(R.id.tankProfile_listRules);
        List<String> tankSkillsHelper;
        tankSkillsHelper = textToList(tank.abilities);
        ArrayAdapter<String> skillsArrayAdapter = new ArrayAdapter<>(
                TankProfileActivity.this,
                android.R.layout.simple_list_item_1,
                tankSkillsHelper
        );
        tankSkills.setAdapter(skillsArrayAdapter);
    }
}