package com.mrdelicious.minitankswot.rosters;

import java.util.Comparator;

public class TankOnList {
    String name;
    int pts, nation, image;

    public TankOnList(String name, int nation, int pts, int image) {
        this.name = name;
        this.nation = nation;
        this.pts = pts;
        this.image = image;
    }

    public static Comparator<TankOnList> TanksPtsComparator = Comparator.comparingInt(TankOnList::getPts);

    @Override
    public String toString() {
        return "TankOnList{" +
                "name='" + name + '\'' +
                ", pts=" + pts +
                ", nation=" + nation +
                ", image=" + image +
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

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
