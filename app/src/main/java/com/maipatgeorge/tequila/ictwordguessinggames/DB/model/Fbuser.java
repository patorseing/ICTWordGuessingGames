package com.maipatgeorge.tequila.ictwordguessinggames.DB.model;

public class Fbuser {
    private String fbID;
    private String name;
    private String token;

    public Fbuser() {
    }

    public Fbuser(String fbID, String name, String token) {
        this.fbID = fbID;
        this.name = name;
        this.token = token;
    }

    public String getFbID() {
        return fbID;
    }

    public void setFbID(String fbID) {
        this.fbID = fbID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
