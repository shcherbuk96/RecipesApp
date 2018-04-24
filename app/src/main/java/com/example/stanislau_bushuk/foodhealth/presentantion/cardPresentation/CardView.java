package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.MvpView;

public interface CardView extends MvpView {
    void showList(Data data);

    void showEditData(EditData data);
}
