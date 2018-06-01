package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.google.firebase.database.DataSnapshot;

import io.reactivex.Observable;

public interface CallBackCardPresenter {
    void call(Observable<Recipe> observable);

    void callFirebase(DataSnapshot dataSnapshot);
}
