package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.Pojo.Recipe;

import io.realm.Realm;

public class RealmModel {
    private Realm realm;

    public RealmModel() {
        realm = Realm.getDefaultInstance();
    }

    private void setRealmObject(final Recipe recipe) {
    }
}
