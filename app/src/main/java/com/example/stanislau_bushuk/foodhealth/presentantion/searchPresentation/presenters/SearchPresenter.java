package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters;


import android.view.View;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.ResourceManager;
import com.example.stanislau_bushuk.foodhealth.model.CallBackSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
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
    NetWorkModel netWorkModel;

    @Inject
    ResourceManager resourceManager;

    public SearchPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBack(this);
    }

    @Override
    public void call(final Observable<Recipes> observable, final boolean update, final int random) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        if (!update)
                            getViewState().progressBarVisible(View.VISIBLE);
                        else getViewState().addProgressBarVisible(View.VISIBLE);
                        Timber.e("subscribe response");
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
                        Timber.e("Error");
                        getViewState().progressBarVisible(View.GONE);
                        getViewState().setSnackBar();
                        getViewState().addProgressBarVisible(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        getViewState().progressBarVisible(View.GONE);
                        getViewState().addProgressBarVisible(View.GONE);
                        Timber.e("Complete");
                    }
                });
    }

    public void searchObservable(final SearchView searchView) {
        RxSearchView.queryTextChanges(searchView)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(final CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim();
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onNext(final String s) {
                        if (!s.isEmpty()) {
                            netWorkModel.getResponse(s, 0, false);
                            getViewState().setSearchText(s);
                        } else {
                            netWorkModel.getRandomRecipe(false);
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
        } else{
            netWorkModel.getResponse(recipeName, from, true);
        }
    }
}
