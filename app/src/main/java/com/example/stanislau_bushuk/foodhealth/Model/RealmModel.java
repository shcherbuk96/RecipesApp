package com.example.stanislau_bushuk.foodhealth.Model;

import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipe;

import io.realm.Realm;

public class RealmModel {
    private Realm realm;

    public RealmModel(){
        realm=Realm.getDefaultInstance();
    }

    private void setRealmObject(Recipe recipe){}
}
