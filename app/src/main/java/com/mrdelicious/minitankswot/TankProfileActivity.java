package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
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
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try { dbHelper.createDataBase(db_name); }
        catch (IOException e) { e.printStackTrace(); }

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);
        String[] stats = getTankStats(name);

        imageFill(name);
        statisticFill(stats);
        showAbilitiesList(stats);

        ListView lvAbilities = findViewById(R.id.tankProfile_listRules);
        lvAbilities.setOnItemClickListener((parent, view, position, id) -> {
            String ability = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(TankProfileActivity.this, AbilityProfileActivity.class);
            intent.putExtra("name", ability);
            startActivity(intent);
        });
    }

    String[] getTankStats(String name){
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
        for (int i = 0; i < listNames.get(1).size(); i++) {
            if (listNames.get(1).get(i).equals(name)){
                index = i;
            }
        }
        for (int i = 0; i < 16; i++) {
            content[i] = listNames.get(i).get(index);
        }

        return content;
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
    void statisticFill(String[] stats){
        String[] helper = {"tankName","costV","firepowerV","defV","moveV","initiativeV","HPV","tierV","typeV","nationV","history"};
        TextView textView;
        int j = 1;
        for (int i = 0; i < 11; i++) {
            String id = "tankProfile_" + helper[i];
            int resID = getResources().getIdentifier(id,"id",getPackageName());
            textView = findViewById(resID);
            textView.setText(stats[j+i]);
            if (j+i==2) j++;
            else if (j+i==8) j+=2;
            else if (j+i==12) j++;
        }
        textView = findViewById(R.id.tankProfile_official);
        if (!stats[3].equals("0")) textView.setVisibility(View.INVISIBLE);
    }
    void imageFill(String name){
        ImageView image = findViewById(R.id.tankProfile_image);
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
    public void showAbilitiesList(String[] stats){
        ListView tankSkills = findViewById(R.id.tankProfile_listRules);
        List<String> tankSkillsHelper;
        tankSkillsHelper = textToList(stats[13]);
        ArrayAdapter<String> skillsArrayAdapter = new ArrayAdapter<>(
                TankProfileActivity.this,
                android.R.layout.simple_list_item_1,
                tankSkillsHelper
        );
        tankSkills.setAdapter(skillsArrayAdapter);
    }
}