package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import io.reactivex.Observable;

public interface CallBackDeepSearchPresenter {
    void callBack(Observable<Recipes> observable);
}
