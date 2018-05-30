package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;

import java.util.List;

import rx.Observable;
import timber.log.Timber;

public class FirebaseModel {

    private final FirebaseDatabase database;
    CallBackCardPresenter callBackCardPresenter;

    public FirebaseModel() {
        database = FirebaseDatabase.getInstance();;
    }

    public void setCallBack(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void getComments(final String nameRecipe) {
        Timber.e(nameRecipe);
        final Observable<DataSnapshot> observable = RxFirebaseDatabase.
                observeValueEvent(database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe));
        Timber.e(String.valueOf(observable));
        callBackCardPresenter.callFirebase(observable);
    }

    public void addComment(final String email, final String text, final String nameRecipe, final String photoUri) {

        final DatabaseReference myRef = database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe).push();
        myRef.setValue(new Comment(email, text, photoUri));
       // getComments(nameRecipe);
    }
}
