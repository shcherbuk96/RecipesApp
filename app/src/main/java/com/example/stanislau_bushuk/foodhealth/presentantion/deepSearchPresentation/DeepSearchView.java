package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;

import java.util.ArrayList;

public interface DeepSearchView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showData(ArrayList<Hits> recipes);

    @StateStrategyType(SkipStrategy.class)
    void progressBarVisibility(int visible);
}
