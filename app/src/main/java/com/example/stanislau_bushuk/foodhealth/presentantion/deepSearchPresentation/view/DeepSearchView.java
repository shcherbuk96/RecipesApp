package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;

import java.util.ArrayList;

public interface DeepSearchView extends MvpView {

    void showData(final ArrayList<Hits> recipes);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void progressBarVisibility(final int visibility);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void notFound();

    @StateStrategyType(SingleStateStrategy.class)
    void clearViewStateCommands();
}
