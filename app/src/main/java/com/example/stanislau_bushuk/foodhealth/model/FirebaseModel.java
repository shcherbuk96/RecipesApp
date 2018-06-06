package com.example.stanislau_bushuk.foodhealth.model;

import android.graphics.Bitmap;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters.AddOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters.OwnRecipesPresenter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import timber.log.Timber;

public class FirebaseModel {

    private final DatabaseReference db;
    private final StorageReference storageReference;
    private CallBackMainActivityPresenter callBackMainActivityPresenter;
    private CallBackOwnRecipesPresenter callBackOwnRecipesPresenter;
    private CallBackAddToOwnRecipesPresenter callBackAddToOwnRecipesPresenter;


    public FirebaseModel() {
        App.getAppComponent().inject(this);
        db = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void setCallBackOwnRecipesPresenter(final OwnRecipesPresenter presenter) {
        this.callBackOwnRecipesPresenter = presenter;
    }

    public void setCallBackMainActivityPresenter(final MainActivityPresenter mainActivityPresenter) {
        this.callBackMainActivityPresenter = mainActivityPresenter;
    }

    public void setCallBackAddToOwnRecipesPresenter(final AddOwnRecipePresenter addOwnRecipePresenter){
        this.callBackAddToOwnRecipesPresenter=addOwnRecipePresenter;
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
                    callBackMainActivityPresenter.addToRealm(new Recipe(ds));
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                Timber.e(databaseError.getMessage());
            }
        });
    }

    public void getOwnRecipes(final String uid) {
        final ArrayList<OwnRecipe> ownRecipes = new ArrayList<>();
        db.child(Constants.OWN_RECIPES).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    ownRecipes.add(new OwnRecipe(ds));
                }
                Timber.e(String.valueOf(ownRecipes.size()));
                callBackOwnRecipesPresenter.callBack(ownRecipes);
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                Timber.e(databaseError.getMessage());
            }
        });
    }

    public void loadImageToStorage(final Bitmap bitmap) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] byteArray = stream.toByteArray();
        final UploadTask uploadTask = storageReference.child(UUID.randomUUID().toString()).putBytes(byteArray);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                callBackAddToOwnRecipesPresenter.getImageUrl(String.valueOf(taskSnapshot.getDownloadUrl()));
            }
        });
    }

    public void sendOwnRecipeToDb(final OwnRecipe recipe,final String uid){
        db.child(Constants.OWN_RECIPES).child(uid).push().setValue(recipe).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(final Void aVoid) {
                callBackAddToOwnRecipesPresenter.sendedRecipe();
            }
        });
    }


    public void deleteRecipeFromDb(final String uid, final String name) {
        final DatabaseReference ref = db.child(Constants.USER);
        ref.child(uid).child(name).removeValue();
    }
}
