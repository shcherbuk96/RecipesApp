package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

public interface CallBackFavorite {
    void addToRealm(Recipe recipe);

    void deleteFromRealm(Recipe recipe);
}
