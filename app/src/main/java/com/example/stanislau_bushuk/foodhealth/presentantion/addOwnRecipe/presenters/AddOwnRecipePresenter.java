package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters;

import android.graphics.Bitmap;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackAddToOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.view.AddOwnRecipeView;

import java.util.ArrayList;

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

    public void validateData(final String recipeName, final ArrayList<String> recipeIngredients,
                             final String recipeInstruction) {


        if (recipeName.isEmpty()) {
            getViewState().fixView(R.id.add_own_recipe_name_editText);
        }

        if (recipeInstruction.isEmpty()) {
            getViewState().fixView(R.id.add_own_recipe_instruction_editText);
        }

        for(final String string : recipeIngredients){
            if(string.isEmpty()){
                getViewState().fixView(R.id.add_own_recipe_ingredients_editText);
            }
        }
    }
}
