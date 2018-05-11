package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters;


import android.view.View;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.ResourceManager;
import com.example.stanislau_bushuk.foodhealth.model.CallBackSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view.ViewSearch;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends MvpPresenter<ViewSearch> implements CallBackSearchPresenter {

    @Inject
    RealmModel realmModel;

    @Inject
    NetWorkModel netWorkModel;

    @Inject
    ResourceManager resourceManager;

    public SearchPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackSearch(this);
    }


    @Override
    public void call(final Observable<Recipes> observable, final boolean update, final boolean db) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Recipes, Recipes>() {
                    @Override
                    public Recipes apply(final Recipes recipes) throws Exception {
                        if (!db) {

                            for (final Hits hits : recipes.getHits()) {
                                final Recipe recipe = hits.getRecipe();
                                realmModel.addToRealm(recipe);
                                recipe.setChecked(realmModel.getIsChecked(recipe));
                            }
                        }

                        return recipes;
                    }
                })
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        if (!update)
                            getViewState().progressBarVisible(View.VISIBLE);
                    }

                    @Override
                    public void onNext(final Recipes recipes) {
                        Timber.e("next response");

                        if (recipes.getCount() != 0) {

                            if (!update) {
                                getViewState().showList(recipes.getHits());
                            } else getViewState().updateList(recipes.getHits());
                        }

                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        getViewState().setSnackBar();
                        getViewState().progressBarVisible(View.GONE);

                        Timber.e("Error");
                    }

                    @Override
                    public void onComplete() {
                        getViewState().progressBarVisible(View.GONE);
                    }
                });
    }

    public void searchObservable(final SearchView searchView) {
        RxSearchView.queryTextChanges(searchView)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(final CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                })
                .debounce(Constants.TEXT_DEBOUNCE, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("TextSubscribe");
                    }

                    @Override
                    public void onNext(final String s) {
                        if (!s.isEmpty()) {

                            netWorkModel.getResponse(s, 0, false);
                            getViewState().setSearchText(s);
                        } else {
                            if (!netWorkModel.checkRealmIsEmpty()) {
                                netWorkModel.getRandomData(false);
                            } else {
                                netWorkModel.getRandomRecipe(false);
                            }
                            getViewState().setSearchText(resourceManager.getString(R.string.search_random));
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void callRandomUpdate(final int from, final String recipeName) {
        if (recipeName.equals(resourceManager.getString(R.string.search_random))) {
            netWorkModel.getRandomRecipe(true);
        } else {
            netWorkModel.getResponse(recipeName, from, true);
        }
    }

    public void refreshData(final String recipeName){
        if (recipeName.equals(resourceManager.getString(R.string.search_random))) {
            netWorkModel.getRandomRecipe(false);
        } else {
            netWorkModel.getResponse(recipeName, 0, false);
        }
    }

    public void addToFavorite(final Recipe recipe) {
        realmModel.addToFavorite(recipe);
    }

    public void deleteFromFavorite(final Recipe recipe) {
        realmModel.deleteFromFavorite(recipe);
    }
}
