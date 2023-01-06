package com.mrdelicious.minitankswot.cards.crits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.cards.CardsDatabase;
import java.util.ArrayList;
import java.util.List;

public class CriticalHitActivity extends AppCompatActivity {

    CardsDatabase db;
    ListView lvCrits;
    ArrayAdapter<String> critArrayAdapter;
    String db_name = "db_cards.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critical);
        lvCrits = findViewById(R.id.critical_listCrits);
        this.setTitle("Uszkodzenia krytyczne");

        db = Room.databaseBuilder(this, CardsDatabase.class, db_name)
                .allowMainThreadQueries()
                .createFromAsset("databases/" + db_name)
                .build();

        ShowCritsOnListView();

        lvCrits.setOnItemClickListener((parent, view, position, id) -> {
            String crit = String.valueOf(parent.getItemAtPosition(position));
            Intent intent = new Intent(CriticalHitActivity.this, RandomCritActivity.class);
            intent.putExtra("name",crit);
            startActivity(intent);
        });
    }
    void ShowCritsOnListView() {
        List<String> crits = db.critDao().getAllNames();
        critArrayAdapter = new ArrayAdapter<>(
                CriticalHitActivity.this,
                android.R.layout.simple_list_item_1,
                crits
        );
        lvCrits.setAdapter(critArrayAdapter);
    }
    public void ShowRandomCrit(View view) {
        Intent intent = new Intent(this,RandomCritActivity.class);
        startActivity(intent);
    }
}