package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class DeepSearchPresenter extends MvpPresenter<DeepSearchView> implements CallBackDeepSearchPresenter{

    @Inject
    NetWorkModelDeepSearch netWorkModel;

    @Inject
    DeepSearchModel model;

    public DeepSearchPresenter(){
        App.getAppComponent().inject(this);
        netWorkModel.setCallBack(this);
    }

    public void getRecipeFilter(final String calories){
        netWorkModel.getRecipeFilter(calories);
    }

    @Override
    public void callBack(final Observable<Recipes> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("Subscribe");
                        getViewState().progressBarVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(final Recipes recipe) {

                        getViewState().showData(recipe.getHits());
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        getViewState().progressBarVisibility(View.INVISIBLE);
                        Timber.e("ERROR");
                    }

                    @Override
                    public void onComplete() {
                        getViewState().progressBarVisibility(View.INVISIBLE);
                    }
                });
    }

}
