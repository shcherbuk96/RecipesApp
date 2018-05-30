package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import javax.inject.Inject;

import rx.Observer;
import timber.log.Timber;

public class FirebaseModel {

    private final DatabaseReference db;
    @Inject
    RealmModel realmModel;
    @Inject
    LoginModel loginModel;

    public FirebaseModel() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void addRecipeToFbDb(final String name, final String uri, final String photoUrl) {

        if (loginModel.getAuth().getUid() != null) {
            final DatabaseReference ref = db.child("USER").child(loginModel.getAuth().getUid());
            ref.child(name).setValue(new RecipeFb(uri, photoUrl, name));
        }

    }

    public void putRecipesFromFBtoRealm() {

        if (loginModel.getAuth().getUid() != null) {
            RxFirebaseDatabase.observeSingleValueEvent(db.child("USER").child(loginModel.getAuth().getUid()))
                    .subscribe(new Observer<DataSnapshot>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(final Throwable e) {
                            Timber.e(e);
                        }

                        @Override
                        public void onNext(final DataSnapshot dataSnapshot) {
                            for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                realmModel.addToRealm(new Recipe(ds));
                            }

                        }
                    });
        }
    }

    public void deleteRecipeFromDb(final String name) {

        if (loginModel.getAuth().getUid() != null) {
            final DatabaseReference ref = db.child("USER");
            ref.child(loginModel.getAuth().getUid()).child(name).removeValue();
        }

    }
}
