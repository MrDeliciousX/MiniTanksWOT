package com.mrdelicious.minitankswot.rosters;

import androidx.appcompat.app.AppCompatActivity;

public class TankOnList extends AppCompatActivity implements Comparable<TankOnList>{
    String name;
    int cost_int;
    String cost;
    int nation;

    public TankOnList(String name, int cost, int nation) {
        this.name = name;
        this.cost_int = cost;
        this.cost = cost + "pkt";
        this.nation = nation;
    }

    public int getCostInt() {
        return cost_int;
    }

    public int getNation() {
        return nation;
    }

    @Override
    public int compareTo(TankOnList t) {
        return cost_int <t.getCostInt()?-1:cost_int> t.getCostInt()?1:doSecondaryOrderSort(t);
    }

    public int doSecondaryOrderSort(TankOnList t) {
        return Integer.compare(nation, t.getNation());
    }
}
