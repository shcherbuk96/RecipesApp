package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.google.firebase.database.DataSnapshot;

public class RecipeFb {
    private String uri;
    private String photoUrl;
    private String name;

    public RecipeFb(final DataSnapshot ds) {
        this.name = (String) ds.child("name").getValue();
        this.uri = (String) ds.child("uri").getValue();
        this.photoUrl = (String) ds.child("photoUrl").getValue();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public RecipeFb(final String uri, final String photoUrl, final String name) {
        this.uri = uri;
        this.photoUrl = photoUrl;
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
