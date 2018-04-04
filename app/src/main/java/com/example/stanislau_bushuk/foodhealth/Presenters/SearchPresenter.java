package com.example.stanislau_bushuk.foodhealth.Presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Model.CallBackSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.Model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.View.ViewSearch;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends MvpPresenter<ViewSearch> implements CallBackSearchPresenter {

    @Inject
    IAPI api;
    @Inject
    NetWorkModel netWorkModel;

    public SearchPresenter() {
        App.getAppComponent().inject(this);
        setCallBack();
    }

    public void setCallBack() {
        netWorkModel.setCallBack(this);
    }

    public void searchRecipes(Observable<Recipes> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.e("subscribe");
                    }

                    @Override
                    public void onNext(Recipes recipes) {
                        Timber.e("next");
                        Timber.e(String.valueOf(recipes.getHits().get(0).getRecipe().getLabel()));
                        Timber.e(String.valueOf(recipes.getTo()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Error");
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("Complete");
                    }
                });
    }

    @Override
    public void call(Observable<Recipes> observable) {
        searchRecipes(observable);
    }
}
