package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.view.OwnRecipesView;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.result.ResultListener;

@InjectViewState
public class OwnRecipesPresenter extends MvpPresenter<OwnRecipesView> implements CallBackOwnRecipesPresenter {

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    LoginModel loginModel;

    @Inject
    OwnRouter router;

    public OwnRecipesPresenter() {
        App.getAppComponent().inject(this);
        firebaseModel.setCallBackOwnRecipesPresenter(this);
        getOwnRecipes();
    }

    @Override
    public void callBack(final List<OwnRecipe> ownRecipes) {
        getViewState().showRecipes(ownRecipes);
    }

    public void getOwnRecipes() {
        firebaseModel.getOwnRecipes(loginModel.getAuth().getUid());
    }

    public void goTo() {
        router.setResultListener(Constants.RESULT_ADDING_RECIPE, new ResultListener() {
            @Override
            public void onResult(final Object resultData) {
                getViewState().addRecipe((OwnRecipe)resultData);
            }
        });
        router.navigateTo(Constants.ADD_OWN_RECIPE, Constants.ADD_OWN_RECIPE);
    }

}
