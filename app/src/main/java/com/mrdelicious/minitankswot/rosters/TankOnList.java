package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;

public class TankOnList extends AppCompatActivity {
    String name;
    String cost;
    int nation;

    public TankOnList(String name, int cost, int nation) {
        this.name = name;
        this.cost = cost + "pkt";
        this.nation = nation;
    }

}
