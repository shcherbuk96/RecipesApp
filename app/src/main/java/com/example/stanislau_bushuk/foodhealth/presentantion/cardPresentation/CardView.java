package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.List;

public interface CardView extends MvpView {
    void showList(List<Recipe> recipe);
}
