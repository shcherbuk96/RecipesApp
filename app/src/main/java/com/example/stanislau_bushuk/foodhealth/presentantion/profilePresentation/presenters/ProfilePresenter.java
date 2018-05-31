package com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.view.ProfileView;

import javax.inject.Inject;

import ru.terrakok.cicerone.result.ResultListener;
import timber.log.Timber;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    @Inject
    OwnRouter ownRouter;

    @Inject
    LoginModel loginModel;

    public ProfilePresenter() {
        App.getAppComponent().inject(this);
    }

    public void checkAuth(){
        if(!loginModel.getAuth().getCurrentUser().isAnonymous()){
            ownRouter.navigateTo(Constants.PROFILE_SCREEN,1);
        }
    }

    public void register(){
        ownRouter.navigateTo(Constants.REGISTRATION_ACTIVITY,Constants.REGISTRATION_ACTIVITY);
    }

    public void signIn(){
        ownRouter.navigateTo(Constants.LOGIN_ACTIVITY,Constants.LOGIN_ACTIVITY);
    }

}
