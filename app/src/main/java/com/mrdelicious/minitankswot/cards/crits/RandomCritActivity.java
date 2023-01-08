package com.mrdelicious.minitankswot.cards.crits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCritActivity extends AppCompatActivity {

    EverythingDatabase db;
    int amountCrits = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_crit);
        this.setTitle("Trafienie krytyczne");
        String name;
        int chanceHelp;

        db = App.getDB(this);
        for (int i = 0; i < db.critDao().getAll().size(); i++) {
            amountCrits += db.critDao().getAll().get(i).amount;
        }

        Bundle clicked = getIntent().getExtras();
        if (clicked != null) name = clicked.getString("name");
        else name = randomCritCard();

        TextView nameCrit = findViewById(R.id.randomCrit_name);
        nameCrit.setText(name);

        TextView textCrit = findViewById(R.id.randomCrit_text);
        textCrit.setText(db.critDao().findByName(name).text);

        TextView rep = findViewById(R.id.randomCrit_isRepairable);
        if (db.critDao().findByName(name).repairable != 1)
            rep.setVisibility(View.INVISIBLE);

        TextView dmg = findViewById(R.id.randomCrit_damageNr);
        dmg.setText(String.valueOf(db.critDao().findByName(name).damage));

        TextView chance = findViewById(R.id.randomCrit_chance);
        chanceHelp = 100*(db.critDao().findByName(name).amount)/amountCrits;
        chance.setText(chanceHelp + "%");

        Button repeatButton = findViewById(R.id.randomCrit_buttonNew);
        repeatButton.setOnClickListener(view ->{
            String newName = randomCritCard();
            nameCrit.setText(newName);
            textCrit.setText(db.critDao().findByName(newName).text);
            if (db.critDao().findByName(newName).repairable != 1)
                rep.setVisibility(View.INVISIBLE);
            dmg.setText(String.valueOf(db.critDao().findByName(newName).damage));
            int chanceHelpNew = 100*(db.critDao().findByName(newName).amount)/amountCrits;
            chance.setText(chanceHelpNew + "%");
        });
    }
    String randomCritCard(){
        Random random = new Random();
        int int_random = random.nextInt(amountCrits);

        List<Crit> crits = db.critDao().getAll();
        List<String> karty = new ArrayList<>();
        for (int i = 0; i < crits.size(); i++) {
            for (int j = 0; j < crits.get(i).amount; j++) {
                karty.add(crits.get(i).critName);
            }
        }
        return karty.get(int_random);
    }
}