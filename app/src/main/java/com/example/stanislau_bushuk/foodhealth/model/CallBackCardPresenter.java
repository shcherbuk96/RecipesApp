package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import io.reactivex.Observable;

public interface CallBackCardPresenter {
    void call(Observable<Recipe> observable);
}
