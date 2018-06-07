package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.google.firebase.auth.AuthResult;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> implements CallBackLoginPresenter {

    @Inject
    LoginModel loginModel;

    @Inject
    OwnRouter router;

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    RealmModel realmModel;

    public LoginPresenter() {
        App.getAppComponent().inject(this);
        loginModel.setCallBack(this);
    }

    public void signInUser(final String email, final String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            loginModel.signIn(email, password);
        } else {
            getViewState().checkEmptyLine();
        }
    }

    public void signInAnonymous() {
        loginModel.signInAnonymous();
    }

    public void registrationUser(final String email, final String password, final String confirm_password) {
        if (email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            getViewState().checkEmptyLine();
        } else {
            if (password.equals(confirm_password)) {
                loginModel.registrationUser(email, password);
            } else {
                getViewState().checkPassword();
            }
        }
    }

    @Override
    public void call(final AuthResult authResult) {
        if (authResult != null) {
            final List<Recipe> recipes = realmModel.getRecipesRealm();

            if (recipes != null) {
                for (final Recipe recipe : recipes) {
                    firebaseModel.addRecipeToFbDb(authResult.getUser().getUid(), recipe.getLabel(),
                            recipe.getUri(), recipe.getImage());
                }
            }

            firebaseModel.putRecipesFromFBtoRealm(authResult.getUser().getUid());
            Timber.e(authResult.getUser().toString());
            getViewState().user(authResult.getUser());
        }
    }

    @Override
    public void fail(final Exception e) {
        getViewState().error(e);
    }


    public void goTo(final String screenKey) {

        if (screenKey.equals(Constants.MAIN_ACTIVITY)) {
            router.newRootScreen(screenKey);
        } else if (screenKey.equals(Constants.PROFILE_SCREEN)) {
            router.exit();
        } else {
            router.navigateTo(screenKey);
        }
    }


    public void replace(final String screenKey) {
        if (screenKey.equals(Constants.MAIN_ACTIVITY)) {
            router.newRootScreen(screenKey);
        } else {
            router.exit();
        }
    }


    public void exit() {
        router.exit();
    }

    public void setViewVisibility(final String keyScreen) {

        if (keyScreen.equals(Constants.PROFILE_SCREEN)) {
            getViewState().setViewVisibility(View.INVISIBLE);
        }
    }
}

