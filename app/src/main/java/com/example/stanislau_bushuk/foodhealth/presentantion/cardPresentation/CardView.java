package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;

import java.util.List;

public interface CardView extends MvpView {
    void showList(Data data);

    void showEditData(EditData data);

    void showComments(List<Comment> listComments);

    @StateStrategyType(SkipStrategy.class)
    void showAnonymous();

    @StateStrategyType(SkipStrategy.class)
    void showError();
}
