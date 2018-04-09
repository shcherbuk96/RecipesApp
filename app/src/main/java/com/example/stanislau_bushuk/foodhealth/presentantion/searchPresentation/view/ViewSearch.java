package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view;

import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;

import java.util.List;

public interface ViewSearch extends MvpView {

    void showList(List<Hits> hitsList);

    void progressBarVisible(int visible);

}
