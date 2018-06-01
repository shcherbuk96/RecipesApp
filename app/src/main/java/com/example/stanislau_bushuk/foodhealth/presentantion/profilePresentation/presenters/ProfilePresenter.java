package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters;

import android.net.Uri;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.view.ProfileView;

import javax.inject.Inject;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

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

    public Fragment getFragment() {
        return fragmentCreater.getNewInstanceFragment(Constants.FAVOURITE_SCREEN, 1);
    }

    public void checkAuth() {
        if (!loginModel.getAuth().getCurrentUser().isAnonymous()) {
            ownRouter.navigateTo(Constants.PROFILE_SCREEN, 1);
        }
    }

    public void register() {
        ownRouter.navigateTo(Constants.REGISTRATION_ACTIVITY, Constants.REGISTRATION_ACTIVITY);
    }

    public void signIn() {
        ownRouter.navigateTo(Constants.LOGIN_ACTIVITY, Constants.LOGIN_ACTIVITY);
    }

    public Uri getUserPhoto() {
        return loginModel.getAuth().getCurrentUser().getPhotoUrl();
    }

    public String getUserEmail() {
        return loginModel.getAuth().getCurrentUser().getEmail();
    }

    public String getUserName() {
        return loginModel.getAuth().getCurrentUser().getDisplayName();
    }

    public int getFavourites() {
        return realmModel.getNumbeFavourites();
    }

}
