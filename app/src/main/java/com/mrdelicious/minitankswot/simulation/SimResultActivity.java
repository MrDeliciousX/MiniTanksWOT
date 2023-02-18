package com.mrdelicious.minitankswot.simulation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.crits.Crit;
import com.mrdelicious.minitankswot.tanks.Tank;

import java.util.List;
import java.util.Locale;

public class SimResultActivity extends AppCompatActivity {

    EverythingDatabase dbTanks;
    Tank tank1, tank2;
    int def1, def2;
    int atk1, atk2;
    String name1, name2;
    Bundle info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_result);
        this.setTitle("Wynik porównania");

        dbTanks = App.getDB(this);

        info = getIntent().getExtras();
        name1 = info.getString("tank1");
        name2 = info.getString("tank2");

        tank1 = dbTanks.tankDao().findByName(name1);
        tank2 = dbTanks.tankDao().findByName(name2);
        if (tank1 == null || tank2 == null) {
            Toast.makeText(this, "Błędna nazwa czołgu/ów", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,SimulationActivity.class);
            startActivity(intent);
        } else {
            wholeThatStatisticShit();
            contentFill();
        }
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

    void contentFill() {
        TextView name = findViewById(R.id.simResult_tankName1);
        name.setText(name1);
        name = findViewById(R.id.simResult_tankName2);
        name.setText(name2);

        ImageView image = findViewById(R.id.simResult_image1);
        imageFill(name1, image);
        image = findViewById(R.id.simResult_image2);
        imageFill(name2, image);
    }

    void wholeThatStatisticShit() {
        def1 = info.getInt("def1") + tank1.survivability;
        def2 = info.getInt("def2") + tank2.survivability;
        if (def1 < 0) def1 = 0;
        if (def2 < 0) def2 = 0;
        atk1 = tank1.firepower;
        atk2 = tank2.firepower;
        boolean advantageRight = info.getBoolean("advantageRight");
        boolean hide1 = info.getBoolean("hide1");
        boolean hide2 = info.getBoolean("hide2");

        List<Crit> critsDmg = dbTanks.critDao().getAll();
        double averageCritDamage = 0.0;
        int amount = 0;
        for (int i = 0; i < critsDmg.size(); i++) {
            averageCritDamage += critsDmg.get(i).damage * critsDmg.get(i).amount;
            amount += critsDmg.get(i).amount;
        }
        averageCritDamage /= amount;

        //zwykłe trafienia
        double averageHits1 = atk1 * (2.0/6.0);
        if (hide2) averageHits1 -= 1;
        if (averageHits1 < 0) averageHits1 = 0;
        else if (tank1.abilities.contains("pociski_kumulacyjne")) averageHits1 = 0;

        //trafienia krytyczne
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SimulationActivity.class);
        startActivity(intent);
    }
}