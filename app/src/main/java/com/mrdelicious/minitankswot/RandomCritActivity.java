package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCritActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String db_name = "db_cards.db";
    String table = "crits";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_crit);
        this.setTitle("Trafienie krytyczne");
        String name;
        int chanceHelp;

        Bundle clicked = getIntent().getExtras();
        if (clicked != null){
            int index = clicked.getInt("index");
            name = findCritNameByID(index);
        } else {
            name = randomCritCard();
        }

        TextView nameCrit = findViewById(R.id.randomCrit_name);

        nameCrit.setText(name);

        TextView textCrit = findViewById(R.id.randomCrit_text);
        textCrit.setText(findCritContent(name)[4]);

        TextView rep = findViewById(R.id.randomCrit_isRepairable);
        if (findCritContent(name)[3].equals("1")){
            rep.setText("Naprawialne");
        } else {
            rep.setText("           ");
        }

        TextView dmg = findViewById(R.id.randomCrit_damageNr);
        dmg.setText(findCritContent(name)[2]);

        TextView chance = findViewById(R.id.randomCrit_chance);
        chanceHelp = 100*(Integer.parseInt(findCritContent(name)[5]))/32;
        chance.setText(chanceHelp + "%");

        Button repeatButton = findViewById(R.id.randomCrit_buttonNew);
        repeatButton.setOnClickListener(view ->{
            String newName = randomCritCard();
            nameCrit.setText(newName);
            textCrit.setText(findCritContent(newName)[4]);
            if (findCritContent(newName)[3].equals("1")){
                rep.setText("Naprawialne");
            } else {
                rep.setText("           ");
            }
            dmg.setText(findCritContent(newName)[2]);
            int chanceHelpNew = 100*(Integer.parseInt(findCritContent(newName)[5]))/32;
            chance.setText(chanceHelpNew + "%");
        });
    }
    String randomCritCard(){
        Random random = new Random();
        int int_random = random.nextInt(32);
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);

        List<Integer> ilosc;
        ilosc = dbHelper.getColumnFromDatabase(db_name, table,5, Cursor::getInt);

        List<String> nazwy;
        nazwy = dbHelper.getColumnFromDatabase(db_name, table, 1, Cursor::getString);

        List<String> karty = new ArrayList<>();
        for (int i = 0; i < ilosc.size(); i++) {
            for (int j = 0; j < ilosc.get(i); j++) {
                karty.add(nazwy.get(i));
            }
        }
        return karty.get(int_random);
    }
    String[] findCritContent(String name){
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        String[] content = new String[6];

        List<String> ids;
        ids = dbHelper.getColumnFromDatabase(db_name,table,0,Cursor::getString);

        List<String> names;
        names = dbHelper.getColumnFromDatabase(db_name,table,1,Cursor::getString);

        List<String> damages;
        damages = dbHelper.getColumnFromDatabase(db_name,table,2,Cursor::getString);

        List<String> reps;
        reps = dbHelper.getColumnFromDatabase(db_name,table,3,Cursor::getString);

        List<String> texts;
        texts = dbHelper.getColumnFromDatabase(db_name,table,4,Cursor::getString);

        List<String> amounts;
        amounts = dbHelper.getColumnFromDatabase(db_name,table,5,Cursor::getString);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)){
                content[0] = ids.get(i);
                content[1] = names.get(i);
                content[2] = damages.get(i);
                content[3] = reps.get(i);
                content[4] = texts.get(i);
                content[5] = amounts.get(i);
                break;
            }
        }
        return content;
    }
    String findCritNameByID(int id){
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        List<String> names;
        names = dbHelper.getColumnFromDatabase(db_name,table,1,Cursor::getString);
        return names.get(id);
    }
}