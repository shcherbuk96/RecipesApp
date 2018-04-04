package com.example.stanislau_bushuk.foodhealth.View;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Hits;

import java.util.List;

public interface ViewSearch extends MvpView {

    public void showList(List<Hits> hitsList);
}
