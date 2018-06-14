package com.example.stanislau_bushuk.foodhealth.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.RecipeFb;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters.AddOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation.CallBackCardOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation.CardOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters.OwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.CallBackProfilePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

import timber.log.Timber;

import static android.support.constraint.Constraints.TAG;

public class FirebaseModel {

    private final FirebaseDatabase database;
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private CallBackMainActivityPresenter callBack;
    private CallBackCardPresenter callBackCardPresenter;
    private CallBackAddToOwnRecipesPresenter callBackAddToOwnRecipesPresenter;
    private CallBackOwnRecipesPresenter callBackOwnRecipesPresenter;
    private CallBackCardOwnRecipePresenter callBackCardOwnRecipePresenter;
    private CallBackProfilePresenter callBackProfilePresenter;


    public FirebaseModel() {
        App.getAppComponent().inject(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
    }

    public void setMainActivityCallBack(final MainActivityPresenter mainActivityPresenter) {
        this.callBack = mainActivityPresenter;
    }

    public void setCallBackAddToOwnRecipesPresenter(final AddOwnRecipePresenter addOwnRecipePresenter) {
        this.callBackAddToOwnRecipesPresenter = addOwnRecipePresenter;
    }

    public void setCardPresenterCallBack(final CardPresenter cardPresenter) {
        callBackCardPresenter = cardPresenter;
    }

    public void setCallBackOwnRecipesPresenter(final OwnRecipesPresenter ownRecipesPresenter) {
        this.callBackOwnRecipesPresenter = ownRecipesPresenter;
    }

    public void setCallBackCardOwnRecipePresenter(final CardOwnRecipePresenter callBackCardOwnRecipePresenter) {
        this.callBackCardOwnRecipePresenter = callBackCardOwnRecipePresenter;
    }

    public void setCallBackProfilePresenter(final ProfilePresenter profilePresenter) {
        this.callBackProfilePresenter = profilePresenter;
    }

    public void addRecipeToFbDb(final String uid, final String name, final String uri,
                                final String photoUrl) {
        final DatabaseReference ref = databaseReference.child(Constants.USER).child(uid);
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

    public void getOwnRecipes(final String uid) {
        final ArrayList<OwnRecipe> ownRecipes = new ArrayList<>();
        databaseReference.child(Constants.OWN_RECIPES).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    final GenericTypeIndicator<OwnRecipe> t = new GenericTypeIndicator<OwnRecipe>() {};
                    ownRecipes.add(ds.getValue(t));
                }

                callBackOwnRecipesPresenter.callBack(ownRecipes);
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                Timber.e(databaseError.getMessage());
            }
        });
    }

    public void getInfoOwnRecipe(final String name, final String uid) {
        databaseReference.child(Constants.OWN_RECIPES).child(uid).child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Timber.e("dataSnapshot != null");
                    Timber.e(dataSnapshot.child("recipeInstruction").getValue(String.class));
                    final GenericTypeIndicator<OwnRecipe> t = new GenericTypeIndicator<OwnRecipe>() {};
                    callBackCardOwnRecipePresenter.call(dataSnapshot.getValue(t));
                } else {
                    Timber.e("dataSnapshot == null");
                    callBackCardOwnRecipePresenter.callError("error");
                }
            }

            @Override
            public void onCancelled(final DatabaseError databaseError) {
                Timber.e(databaseError.getMessage());
                callBackCardOwnRecipePresenter.callError(databaseError.getMessage());
            }
        });
    }

    public void getComments(final String nameRecipe) {
        final DatabaseReference databaseReference = database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe);

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

    public void addComment(final String email, final String text, final String nameRecipe,
                           final String photoUri) {
        final DatabaseReference myRef = database.getReference(Constants.FIREBASE_COMMENTS_BRANCH).child(nameRecipe).push();
        myRef.setValue(new Comment(email, text, photoUri));
    }

    public void loadImageToStorage(final Uri uri) {
        final UploadTask uploadTask = storageReference.child(UUID.randomUUID().toString() + "/img.jpg").putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                callBackAddToOwnRecipesPresenter.getImageUrl(String.valueOf(taskSnapshot.getDownloadUrl()));
            }
        });
    }

    public void loadPhotoUserToStorage(final Uri uri) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callBackProfilePresenter.showPhoto();
                        }
                    }
                });
    }

    public void sendOwnRecipeToDb(final OwnRecipe recipe, final String uid) {
        databaseReference.child(Constants.OWN_RECIPES).child(uid).child(recipe.getRecipeName()).setValue(recipe).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(final Void aVoid) {
                callBackAddToOwnRecipesPresenter.sendedRecipe();
            }
        });
    }


    public void deleteRecipeFromDb(final String uid, final String name) {
        final DatabaseReference ref = database.getReference().child(Constants.USER);
        ref.child(uid).child(name).removeValue();
    }
}

