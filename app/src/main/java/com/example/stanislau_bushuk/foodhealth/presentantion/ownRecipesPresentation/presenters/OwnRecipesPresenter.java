package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view.OwnRecipesView;

import javax.inject.Inject;

@InjectViewState
public class OwnRecipesPresenter extends MvpPresenter<OwnRecipesView> {

    public OwnRecipesPresenter(){
        App.getAppComponent().inject(this);
    }

}
