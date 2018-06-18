package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.CallBackProfilePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.ProfileView;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> implements CallBackProfilePresenter {

    @Inject
    OwnRouter ownRouter;

    @Inject
    LoginModel loginModel;

    @Inject
    RealmModel realmModel;

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    FragmentCreater fragmentCreater;

    public ProfilePresenter() {
        App.getAppComponent().inject(this);
        firebaseModel.setCallBackProfilePresenter(this);
    }

    public void checkAuth() {
        if (!loginModel.getAuth().getCurrentUser().isAnonymous()) {
            ownRouter.navigateTo(Constants.PROFILE_SCREEN, 1);
        }
    }

    public void loadImage(Uri uri) {
        firebaseModel.loadPhotoUserToStorage(uri);
    }

    public void getUserPhoto(String uid) {
        firebaseModel.getUserPhoto(uid);
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

    @Override
    public void showPhoto(String url) {
        getViewState().showPhotoUser(url);
    }
}
