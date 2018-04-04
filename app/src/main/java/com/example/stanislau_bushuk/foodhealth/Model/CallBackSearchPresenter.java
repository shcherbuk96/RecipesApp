package com.example.stanislau_bushuk.foodhealth.Model;


import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipes;

import io.reactivex.Observable;

public interface CallBackSearchPresenter {
    void call(Observable<Recipes> observable);
}
