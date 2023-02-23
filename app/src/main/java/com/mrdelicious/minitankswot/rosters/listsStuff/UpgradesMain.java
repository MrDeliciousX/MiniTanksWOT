package com.mrdelicious.minitankswot.rosters.listsStuff;

public class UpgradesMain {
    String name, pts;

    public UpgradesMain(String name, String pts) {
        this.name = name;
        this.pts = pts;
    }

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

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }
}
