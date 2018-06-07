package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<MvpView> {

    @Inject
    OwnRouter ownRouter;

    @Inject
    LoginModel loginModel;

    @Inject
    RealmModel realmModel;

    @Inject
    FragmentCreater fragmentCreater;

    public ProfilePresenter() {
        App.getAppComponent().inject(this);
    }

    public void checkAuth() {
        if (!loginModel.getAuth().getCurrentUser().isAnonymous()) {
            ownRouter.navigateTo(Constants.PROFILE_SCREEN, 1);
        }
    }

    public void register() {
        ownRouter.navigateTo(Constants.REGISTRATION_ACTIVITY, Constants.PROFILE_SCREEN);
    }

    public void signIn() {
        ownRouter.navigateTo(Constants.LOGIN_ACTIVITY, Constants.PROFILE_SCREEN);
    }

    public FirebaseUser getUser() {
        return loginModel.getAuth().getCurrentUser();
    }

}
