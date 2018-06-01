package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;

import java.util.List;

public interface ViewSearch extends MvpView {

    void showList(List<Hits> hitsList);

    void updateList(List<Hits> hitsList);

    @StateStrategyType(SkipStrategy.class)
    void progressBarVisible(final int visible);

    void setSearchText(String text);

    @StateStrategyType(SkipStrategy.class)
    void setSnackBar();

}
