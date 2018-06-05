package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackAddToOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.view.AddOwnRecipeView;

import javax.inject.Inject;

@InjectViewState
public class AddOwnRecipePresenter extends MvpPresenter<AddOwnRecipeView> implements CallBackAddToOwnRecipesPresenter {

    @Inject
    OwnRouter router;

    @Inject
    FirebaseModel model;

    public AddOwnRecipePresenter() {
        App.getAppComponent().inject(this);
        model.setCallBackAddToOwnRecipesPresenter(this);
    }

    public void exit() {
        router.exit();
    }

    public void loadImageToStorage(final Bitmap bitmap) {
        model.loadImageToStorage(bitmap);
    }


    @Override
    public void getImageUrl(final String url) {
        getViewState().showImage(url);
    }
}
