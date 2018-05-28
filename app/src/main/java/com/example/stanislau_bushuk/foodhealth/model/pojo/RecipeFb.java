package com.example.stanislau_bushuk.foodhealth.model.pojo;

public class RecipeFb {
    private String uri;
    private String photoUrl;
    private String name;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public RecipeFb(String uri, String photoUrl, String name) {
        this.uri = uri;
        this.photoUrl = photoUrl;
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
