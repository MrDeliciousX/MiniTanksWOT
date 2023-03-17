package com.mrdelicious.minitankswot.cards.upgrades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.GameActivity;
import com.mrdelicious.minitankswot.R;

import java.util.List;

public class UpgradesActivity extends AppCompatActivity {
    EverythingDatabase db;
    RecyclerView rwUpg;
    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<UpgradeOnList> upgradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);
        this.setTitle("Ulepszenia");

        db = App.getDB(this);

        //rwUpg = findViewById(R.id.upgrades_list);
        layoutManager = new LinearLayoutManager(this);
        listAdapter = new UpgradeOnListAdapter(upgradeList, UpgradesActivity.this);

        showUpgradesList();
    }

    private void showUpgradesList() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}