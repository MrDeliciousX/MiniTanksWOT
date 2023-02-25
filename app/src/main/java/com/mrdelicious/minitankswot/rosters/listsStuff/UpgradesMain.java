package com.mrdelicious.minitankswot.rosters.listsStuff;

import java.util.Comparator;

public class UpgradesMain {
    String name;
    int pts;

    public UpgradesMain(String name, int pts) {
        this.name = name;
        this.pts = pts;
    }

    public static Comparator<UpgradesMain> UpgradesPtsComparator = Comparator.comparingInt(UpgradesMain::getPts);

    @Override
    public String toString() {
        return "UpgradesMain{" +
                "name='" + name + '\'' +
                ", pts=" + pts +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
