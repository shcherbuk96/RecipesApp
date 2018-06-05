package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import timber.log.Timber;

public class FirebaseModel {

    private final FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private CallBackMainActivityPresenter callBack;
    private CallBackCardPresenter callBackCardPresenter;

    public FirebaseModel() {
        App.getAppComponent().inject(this);
        database = FirebaseDatabase.getInstance();
    }

    public void setMainActivityCallBack(final MainActivityPresenter mainActivityPresenter) {
        this.callBack = mainActivityPresenter;
    }

    public void setCardPresenterCallBack(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void addRecipeToFbDb(final String uid, final String name, final String uri, final String photoUrl) {
        final DatabaseReference ref = database.getReference().child(Constants.USER).child(uid);
        ref.child(name).setValue(new RecipeFb(uri, photoUrl, name));
    }

    public void putRecipesFromFBtoRealm(final String uid) {
        database.getReference().child(Constants.USER).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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

    public void getComments(final String nameRecipe) {
        databaseReference = database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                callBackCardPresenter.callFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                callBackCardPresenter.callFirebase(null);
            }
        });

    }

    public void addComment(final String email, final String text, final String nameRecipe, final String photoUri) {
        final DatabaseReference myRef = database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe).push();
        myRef.setValue(new Comment(email, text, photoUri));
    }


    public void deleteRecipeFromDb(final String uid, final String name) {
        final DatabaseReference ref = database.getReference().child(Constants.USER);
        ref.child(uid).child(name).removeValue();
    }

}
