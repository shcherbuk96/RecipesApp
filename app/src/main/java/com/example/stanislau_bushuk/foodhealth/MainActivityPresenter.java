package com.example.stanislau_bushuk.foodhealth;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackMainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MvpView> implements CallBackMainActivityPresenter {

    private final DatabaseReference db;
    @Inject
    OwnRouter router;
    @Inject
    FirebaseModel firebaseModel;
    @Inject
    LoginModel loginModel;
    @Inject
    RealmModel realmModel;

    public MainActivityPresenter() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
        firebaseModel.setCallBack(this);
    }

    public void goBack(final String screenKey, final int data) {
        router.backToOwn(screenKey, data);
    }

    public void goTo(final String screenKey) {
        router.navigateTo(screenKey, 1);
    }

    public void checkDeferencesFBandRealm() {

        if (db.child(Constants.USER).child(loginModel.getAuth().getUid()).getKey() != null &&
                realmModel.getRecipesRealm().size() == 0) {
            firebaseModel.putRecipesFromFBtoRealm(loginModel.getAuth().getUid());
        }

    }

    @Override
    public void addToRealm(final Recipe recipe) {
        realmModel.addToRealm(recipe);
    }
}
