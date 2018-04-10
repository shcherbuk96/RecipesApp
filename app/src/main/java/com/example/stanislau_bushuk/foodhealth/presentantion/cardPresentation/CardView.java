package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

public interface CardView {
    void showList(Recipe recipe);

    void progressBarVisible(final int visible);
}
