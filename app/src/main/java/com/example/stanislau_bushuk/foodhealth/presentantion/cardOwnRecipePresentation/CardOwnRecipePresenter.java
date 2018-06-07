package com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

import javax.inject.Inject;

@InjectViewState
public class CardOwnRecipePresenter extends MvpPresenter<CardOwnRecipeView> implements CallBackCardOwnRecipePresenter {

    @Inject
    OwnRouter ownRouter;

    @Inject
    FirebaseModel firebaseModel;

    CardOwnRecipePresenter() {
        App.getAppComponent().inject(this);
        firebaseModel.setCallBackCardOwnRecipePresenter(this);
    }

    public void getInfoOwnRecipe(final String name, final String uid) {
        if (name != null && uid != null) {
            firebaseModel.getInfoOwnRecipe(name, uid);
        }
    }


    @Override
    public void call(final OwnRecipe ownRecipe) {
        getViewState().infoOwnRecipe(ownRecipe);
    }

    @Override
    public void callError(final String error) {
        if (error != null) {
            getViewState().error(error);
        }
    }

    public void back() {
        ownRouter.exit();
    }
}
