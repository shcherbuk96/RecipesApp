package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.google.firebase.database.DataSnapshot;

public interface CallBackCardPresenter {
    void call(io.reactivex.Observable<Recipe> observable);

    void callFirebase(rx.Observable<DataSnapshot> observable);
}
