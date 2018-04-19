package com.example.stanislau_bushuk.foodhealth.model.pojo;

import io.realm.RealmObject;

public class Ingridients extends RealmObject {
    private String text;
    private float weight;

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float weight) {
        this.weight = weight;
    }
}
