package com.mrdelicious.minitankswot.cards.upgrades;

import java.util.Comparator;

public class UpgradeOnList {
    private String name, type;
    private int pts, nation;

    public UpgradeOnList(String name, String type, int pts, int nation) {
        this.name = name;
        this.type = type;
        this.pts = pts;
        this.nation = nation;
    }

    public static Comparator<UpgradeOnList> UpgradesPtsComparator = Comparator.comparing(UpgradeOnList::getPts);
    public static Comparator<UpgradeOnList> UpgradesNationComparator = Comparator.comparing(UpgradeOnList::getNation);

    @Override
    public String toString() {
        return "UpgradeOnList{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pts=" + pts +
                ", nation=" + nation +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }
}
