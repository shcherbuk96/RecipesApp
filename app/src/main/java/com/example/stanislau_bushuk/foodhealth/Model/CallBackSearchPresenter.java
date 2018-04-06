package com.example.stanislau_bushuk.foodhealth.model;


import com.example.stanislau_bushuk.foodhealth.model.Pojo.Recipes;

import io.reactivex.Observable;

public interface CallBackSearchPresenter {
    void call(Observable<Recipes> observable);
}
