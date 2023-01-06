package com.mrdelicious.minitankswot;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;

public class AbilityProfileActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String db_name = "db_rules.db";
    String table = "abilities";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_profile);

        Bundle clicked = getIntent().getExtras();
        String name = clicked.getString("name");
        this.setTitle(name);

        TextView title = findViewById(R.id.abilityProfile_name);
        title.setText(name);

        TextView official = findViewById(R.id.abilityProfile_official);
        TextView text = findViewById(R.id.abilityProfile_text);

        String[] ability = getAbilityContent(name);
        text.setText(ability[1]);

        if (ability[2].equals("1"))
            official.setVisibility(View.INVISIBLE);
    }
    String[] getAbilityContent(String name){
        dbHelper = new DatabaseHelper(getApplicationContext(),db_name);
        try {
            dbHelper.createDataBase(db_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] content = new String[3];
        List<String> names = dbHelper.getColumnFromDatabase(db_name,table,1, Cursor::getString);
        List<String> texts = dbHelper.getColumnFromDatabase(db_name,table,2,Cursor::getString);
        List<String> officials = dbHelper.getColumnFromDatabase(db_name,table,3,Cursor::getString);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)){
                content[0] = name;
                content[1] = texts.get(i);
                content[2] = officials.get(i);
            }
        }
        return content;
    }
}