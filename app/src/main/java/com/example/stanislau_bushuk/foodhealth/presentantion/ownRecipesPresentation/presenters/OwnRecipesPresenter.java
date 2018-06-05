package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackMainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view.OwnRecipesView;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class OwnRecipesPresenter extends MvpPresenter<OwnRecipesView> implements CallBackOwnRecipesPresenter {

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    OwnRouter router;

    public OwnRecipesPresenter(){
        App.getAppComponent().inject(this);
        firebaseModel.setCallBackOwnRecipesPresenter(this);
    }

    @Override
    public void callBack(final List<OwnRecipe> ownRecipes) {

    }

    public void goTo(){
        router.navigateTo(Constants.ADD_OWN_RECIPE, Constants.ADD_OWN_RECIPE);
    }

}
