package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> implements CallBackCardPresenter{
    @Inject
    NetWorkModel netWorkModel;

    public CardPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackCard(this);
    }

    public void getRecipeFromUri(String uri) {
        netWorkModel.getRecipeFromUri(uri);
    }

    @Override
    public void call(final Observable<Recipe> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipe>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        getViewState().progressBarVisible(View.VISIBLE);
                        Timber.e("onSubscribe");
                    }

                    @Override
                    public void onNext(final Recipe recipe) {
                        Timber.e("onNext");
                        if (recipe!=null);
                           // getViewState().showList(recipe);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("onComplete");
                        getViewState().progressBarVisible(View.INVISIBLE);
                    }
                });
    }
}
