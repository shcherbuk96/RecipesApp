package com.example.stanislau_bushuk.foodhealth.model.pojo;

import com.google.firebase.database.DataSnapshot;

public class Comment {
    private String email;
    private String photoUri;
    private String text;


    public Comment(final String email, final String text, final String photoUri) {
        this.email = email;
        this.text = text;
        this.photoUri = photoUri;
    }

    public Comment(final DataSnapshot ds) {
        this.email = (String) ds.child("email").getValue();
        this.photoUri = (String) ds.child("photoUri").getValue();
        this.text = (String) ds.child("text").getValue();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(final String photoUri) {
        this.photoUri = photoUri;
    }
}
