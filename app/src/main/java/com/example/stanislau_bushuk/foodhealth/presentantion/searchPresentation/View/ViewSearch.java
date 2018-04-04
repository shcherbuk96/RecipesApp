package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.View;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Hits;

import java.util.List;

public interface ViewSearch extends MvpView {

    void showList(List<Hits> hitsList);

    void showProgressBar();

    void closeProgressBar();
}
