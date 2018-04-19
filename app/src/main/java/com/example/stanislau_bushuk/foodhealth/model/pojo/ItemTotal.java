package com.example.stanislau_bushuk.foodhealth.model.pojo;

import io.realm.RealmObject;

public class ItemTotal extends RealmObject {
    private String label;
    private float quantity;
    private String unit;


    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getLabel() {
        return label;
    }


}