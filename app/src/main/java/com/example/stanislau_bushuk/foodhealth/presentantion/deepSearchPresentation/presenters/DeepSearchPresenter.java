package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters;

import android.view.View;
import android.widget.CompoundButton;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.CallBackDeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.DeepSearchModel;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.NetWorkModelDeepSearch;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.view.DeepSearchView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class DeepSearchPresenter extends MvpPresenter<DeepSearchView> implements CallBackDeepSearchPresenter {

    @Inject
    public DeepSearchModel model;

    @Inject
    RealmModel realmModel;

    @Inject
    NetWorkModelDeepSearch netWorkModel;

    public DeepSearchPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBack(this);
    }

    public void setStartFrom() {
        model.setFrom(0);
    }

    public void getRecipeFilter(final String caloriesFrom, final String caloriesTo, final String upToIngredients) {
        final int from = model.getFrom();

        if (upToIngredients.isEmpty()) {

            if (caloriesTo.isEmpty() || caloriesFrom.isEmpty()) {
                netWorkModel.getRecipeFilter(Constants.CALLORIES, from, model.getQueryMap());
                model.setCalories(Constants.CALLORIES);
            } else {
                final String calories = caloriesFrom + Constants.HYPHEN + caloriesTo;
                netWorkModel.getRecipeFilter(calories, from, model.getQueryMap());
                model.setCalories(calories);
            }

        } else {

            if (caloriesTo.isEmpty() || caloriesFrom.isEmpty()) {
                netWorkModel.getRecipeFilterCountIngredients(Constants.CALLORIES, from, model.getQueryMap(),
                        upToIngredients);
                model.setCalories(Constants.CALLORIES);
            } else {
                final String calories = caloriesFrom + Constants.HYPHEN + caloriesTo;
                netWorkModel.getRecipeFilterCountIngredients(calories, from, model.getQueryMap(),
                        upToIngredients);
                model.setCalories(calories);
            }

        }

        model.setFrom(from + Constants.ITEMS_IN_PAGE + 1);
    }

    public void updateRecipeFilter() {
        netWorkModel.getRecipeFilter(model.getCalories(), model.getFrom(), model.getQueryMap());
        final int from = model.getFrom();
        model.setFrom(from + Constants.ITEMS_IN_PAGE + 1);
    }

    @Override
    public void callBack(final Observable<Recipes> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Function<Recipes, Recipes>() {
                    @Override
                    public Recipes apply(final Recipes recipes) throws Exception {

                        for (final Hits hits : recipes.getHits()) {
                            final Recipe recipe = hits.getRecipe();
                            realmModel.addToRealm(recipe);
                            recipe.setChecked(realmModel.getIsChecked(recipe));
                        }

                        return recipes;
                    }
                })
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        getViewState().progressBarVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(final Recipes recipe) {

                        if (recipe.getHits().size() == 0) {
                            getViewState().notFound();
                        }

                        getViewState().showData(recipe.getHits());
                    }

                    @Override
                    public void onError(final Throwable e) {
                        getViewState().progressBarVisibility(View.INVISIBLE);
                        Timber.e(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        getViewState().progressBarVisibility(View.INVISIBLE);
                    }
                });
    }

    public void setMap(final CompoundButton compoundButton, final boolean checked, final String label) {
        model.setMap(compoundButton, checked, label);
    }

    public void addToFavorite(final Recipe recipe) {
        realmModel.addToFavorite(recipe);
    }

    public void deleteFromFavorite(final Recipe recipe) {
        realmModel.deleteFromFavorite(recipe);
    }
}
