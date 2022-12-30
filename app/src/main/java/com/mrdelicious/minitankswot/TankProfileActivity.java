package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TankProfileActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String db_name = "db_tanks.db";
    String table = "TANKS_TABLE";
    List<String> names, sList, oList, mList, iList, hpList, hpColors, crews, tiers, types, skillList, nations, histList, costs, officials, ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");

        String[] stats = getTankStats(name);

        TextView tankName = findViewById(R.id.tankProfile_tankName);
        tankName.setText(name);

        ImageView image = findViewById(R.id.tankProfile_image);
        String nameHelper = name.toLowerCase(Locale.ROOT);
        int imageHelper = getResources().getIdentifier(nameHelper, "drawable", getPackageName());
        image.setImageResource(imageHelper);

        TextView tankS = findViewById(R.id.tankProfile_firepowerV);
        tankS.setText(stats[4]);

        TextView tankO = findViewById(R.id.tankProfile_defV);
        tankO.setText(stats[5]);

        TextView tankM = findViewById(R.id.tankProfile_moveV);
        tankM.setText(stats[6]);

        TextView tankI = findViewById(R.id.tankProfile_initiativeV);
        tankI.setText(stats[7]);

        TextView tankCost = findViewById(R.id.tankProfile_costV);
        tankCost.setText(stats[2]);

        TextView tankHP = findViewById(R.id.tankProfile_HPV);
        tankHP.setText(stats[8]);

        TextView tankTier = findViewById(R.id.tankProfile_tierV);
        tankTier.setText(stats[11]);

        TextView tankType = findViewById(R.id.tankProfile_typeV);
        tankType.setText(stats[12]);

        ListView tankSkills = findViewById(R.id.tankProfile_listRules);
        List<String> tankSkillsHelper;
        tankSkillsHelper = textToList(stats[13]);
        ArrayAdapter<String> skillsArrayAdapter = new ArrayAdapter<>(
                TankProfileActivity.this,
                android.R.layout.simple_list_item_1,
                tankSkillsHelper
        );
        tankSkills.setAdapter(skillsArrayAdapter);

        TextView tankNation = findViewById(R.id.tankProfile_nationV);
        tankNation.setText(stats[14]);

        TextView tankHistory = findViewById(R.id.tankProfile_history);
        tankHistory.setText(stats[15]);
    }

    public String[] getTankStats(String name){
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        String[] content = new String[16];
        List<List<String>> listNames = new ArrayList<>();
        listNames.add(ids);
        listNames.add(names);
        listNames.add(costs);
        listNames.add(officials);
        listNames.add(sList);
        listNames.add(oList);
        listNames.add(mList);
        listNames.add(iList);
        listNames.add(hpList);
        listNames.add(hpColors);
        listNames.add(crews);
        listNames.add(tiers);
        listNames.add(types);
        listNames.add(skillList);
        listNames.add(nations);
        listNames.add(histList);

        for (int i = 0; i < 16; i++) {
            listNames.set(i,dbHelper.getColumnFromDatabase(db_name,table,i,Cursor::getString));
        }
        int index = 0;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)){
                index = i;
            }
        }
        for (int i = 0; i < 16; i++) {
            content[i] = listNames.get(i).get(index);
        }

        return content;
    }
    public List<String> textToList(String text){
        List<String> lista = new ArrayList<>();

        return lista;
    }
}