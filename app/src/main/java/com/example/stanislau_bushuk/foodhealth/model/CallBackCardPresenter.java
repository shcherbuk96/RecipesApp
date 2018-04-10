package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface CallBackCardPresenter {
    void call(Observable<Recipe> observable);
}
