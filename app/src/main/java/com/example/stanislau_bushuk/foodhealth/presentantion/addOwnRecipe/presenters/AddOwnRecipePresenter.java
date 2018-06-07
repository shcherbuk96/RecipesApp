package com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters;

import android.graphics.Bitmap;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackAddToOwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.view.AddOwnRecipeView;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginModel;

import java.util.ArrayList;

import javax.inject.Inject;

@InjectViewState
public class AddOwnRecipePresenter extends MvpPresenter<AddOwnRecipeView> implements CallBackAddToOwnRecipesPresenter {

    @Inject
    OwnRouter router;

    @Inject
    LoginModel loginModel;

    @Inject
    FirebaseModel model;

    private OwnRecipe ownRecipe;

    public AddOwnRecipePresenter() {
        App.getAppComponent().inject(this);
        model.setCallBackAddToOwnRecipesPresenter(this);
    }

    public void exit() {
        router.exit();
    }

    public void loadImageToStorage(final Uri uri) {
        model.loadImageToStorage(uri);
    }


    @Override
    public void getImageUrl(final String url) {
        getViewState().showImage(url);
    }

    @Override
    public void sendedRecipe() {
        router.exitWithResult(Constants.RESULT_ADDING_RECIPE,ownRecipe);
        router.exit();
    }

    public void validateData(final String recipeName, final ArrayList<String> recipeIngredients,
                             final String recipeInstruction,final String imageUrl) {
        boolean isValid=true;

        if (recipeName.isEmpty()) {
            getViewState().fixView(R.id.add_own_recipe_name_editText);
            isValid=false;
        }

        if (recipeInstruction.isEmpty()) {
            getViewState().fixView(R.id.add_own_recipe_instruction_editText);
            isValid=false;
        }

        for(final String string : recipeIngredients){
            if(string.isEmpty()){
                getViewState().fixView(R.id.add_own_recipe_ingredients_editText);
                isValid=false;
                break;
            }
        }

        ownRecipe = new OwnRecipe(recipeName,recipeInstruction,imageUrl,recipeIngredients);
        if(isValid){
            model.sendOwnRecipeToDb(ownRecipe,loginModel.getAuth().getUid());
        }
    }
}
