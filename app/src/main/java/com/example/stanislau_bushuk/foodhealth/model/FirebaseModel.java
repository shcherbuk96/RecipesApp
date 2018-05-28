package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import javax.inject.Inject;

public class FirebaseModel {

    @Inject
    RealmModel realmModel;

    @Inject
    LoginModel loginModel;

    private DatabaseReference db;

    public FirebaseModel() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void getListRecipe(){
       // RxFirebaseDatabase.
    }

    public void addRecipeToFbDb(String name, String uri, String photoUrl) {

        if (loginModel.getAuth().getUid() != null) {
            DatabaseReference ref = db.child("USER").child(loginModel.getAuth().getUid());
            ref.child(name).setValue(new RecipeFb(uri, photoUrl, name));
        }

    }

    public void deleteRecipeFromDb(String name) {

        if (loginModel.getAuth().getUid() != null) {
            DatabaseReference ref = db.child("USER");
            ref.child(loginModel.getAuth().getUid()).child(name).removeValue();
        }

    }
}
