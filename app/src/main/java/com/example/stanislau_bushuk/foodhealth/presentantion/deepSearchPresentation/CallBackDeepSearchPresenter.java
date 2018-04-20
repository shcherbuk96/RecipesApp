package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import io.reactivex.Observable;

/**
 * Created by logoped583st on 20.4.18.
 */

public interface CallBackDeepSearchPresenter {
    void callBack(Observable <Recipes> observable);
}
