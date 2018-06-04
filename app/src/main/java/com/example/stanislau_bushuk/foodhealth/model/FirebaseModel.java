package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import timber.log.Timber;

public class FirebaseModel {

    private final DatabaseReference db;
    private CallBackMainActivityPresenter callBack;


    public FirebaseModel() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void setCallBack(final MainActivityPresenter mainActivityPresenter) {
        this.callBack = mainActivityPresenter;
    }

    public void addRecipeToFbDb(final String uid, final String name, final String uri, final String photoUrl) {
        final DatabaseReference ref = db.child(Constants.USER).child(uid);
        ref.child(name).setValue(new RecipeFb(uri, photoUrl, name));
    }

    public void putRecipesFromFBtoRealm(final String uid) {
        db.child(Constants.USER).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    callBack.addToRealm(new Recipe(ds));
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                Timber.e(databaseError.getMessage());
            }
        });
    }


    public void deleteRecipeFromDb(final String uid, final String name) {
        final DatabaseReference ref = db.child(Constants.USER);
        ref.child(uid).child(name).removeValue();
    }
}
