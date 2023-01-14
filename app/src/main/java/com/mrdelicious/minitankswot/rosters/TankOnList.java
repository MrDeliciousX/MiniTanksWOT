package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;

public class TankOnList extends AppCompatActivity {
    String name;
    String cost;
    String nation;
    int nation_image;

    public TankOnList(String name, int cost, String nation) {
        this.name = name;
        this.cost = cost + "pkt";
        this.nation = nation;
        this.nation_image = image(name);
    }

    int image(String name) {
        StringBuilder nameHelper = new StringBuilder();
        nameHelper.append("flag_");
        for (int i = 0; i < name.length(); i++) {
            nameHelper.append(name.charAt(i));
        }
        return getResources().getIdentifier(nameHelper.toString(), "drawable", getPackageName());
    }
}
