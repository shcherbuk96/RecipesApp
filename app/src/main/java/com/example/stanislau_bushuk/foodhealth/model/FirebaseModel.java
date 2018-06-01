package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseModel {

    private final FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private CallBackCardPresenter callBackCardPresenter;

    public FirebaseModel() {
        database = FirebaseDatabase.getInstance();
    }

    public void setCallBack(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
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
}
