package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters;


import android.view.View;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
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

    public SearchPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBack(this);
    }

    @Override
    public void call(final Observable<Recipes> observable, final boolean update) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        getViewState().progressBarVisible(View.VISIBLE);
                        Timber.e("subscribe");
                    }

                    @Override
                    public void onNext(final Recipes recipes) {

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
                    }

                    @Override
                    public void onComplete() {
                        getViewState().progressBarVisible(View.INVISIBLE);
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
                        Timber.e("subsctibe");
                    }

                    @Override
                    public void onNext(final String s) {
                        if (!s.isEmpty()) {
                            netWorkModel.getResponse(s, 0, false);
                            getViewState().setSearchText(s);
                        } else {
                            netWorkModel.getRandomRecipe(false);
                            getViewState().setSearchText("Random 10 recipes");
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.e("complete");
                    }
                });
    }

    public void checkUpdate(int itemCout, int lastItem, String nameRecipe) {
        Timber.e("count " + itemCout + " lastItem " + lastItem);

        if (itemCout == lastItem + 1) {
            getViewState().setReadyScroll();
            netWorkModel.getResponse(nameRecipe, lastItem + 1, true);
        }

    }
}
