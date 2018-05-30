package com.example.stanislau_bushuk.foodhealth;


import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class MainActivityPresenter extends MvpPresenter<MvpView> {

    @Inject
    Router router;

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    LoginModel loginModel;

    @Inject
    RealmModel realmModel;

    private final DatabaseReference db;

    public MainActivityPresenter() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void goBack(final String screenKey) {
        router.backTo(screenKey);
    }

    public void goTo(final String screenKey) {
        router.navigateTo(screenKey, 1);
    }

    public void checkDeferencesFBandRealm() {
       if(db.child("USER").child(loginModel.getAuth().getUid()).getKey()!=null &&
               realmModel.getRecipesRealm().size()==0){
           firebaseModel.putRecipesFromFBtoRealm();
       }
    }

}
