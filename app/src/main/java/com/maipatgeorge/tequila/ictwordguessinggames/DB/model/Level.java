package com.maipatgeorge.tequila.ictwordguessinggames.DB.model;

public class Level {
    private String Q;
    private int catID;

    public Level() {
    }

    public Level(String q, int catID) {
        Q = q;
        this.catID = catID;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }
}
