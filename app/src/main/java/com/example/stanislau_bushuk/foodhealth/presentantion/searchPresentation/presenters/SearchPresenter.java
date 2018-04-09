package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view.ViewSearch;

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
    NetWorkModel netWorkModel;

    public SearchPresenter() {
        App.getAppComponent().inject(this);
        setCallBack();
    }

    private void setCallBack() {
        netWorkModel.setCallBack(this);
    }


    private void searchRecipes(final Observable<Recipes> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipes>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        getViewState().showProgressBar();
                        Timber.e("subscribe");
                    }

                    @Override
                    public void onNext(final Recipes recipes) {
                        Timber.e("next");
                        Timber.e(String.valueOf(recipes.getHits().get(0).getRecipe().getLabel()));
                        Timber.e(String.valueOf(recipes.getTo()));
                        getViewState().showList(recipes.getHits());
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        Timber.e("Error");
                    }

                    @Override
                    public void onComplete() {
                        getViewState().closeProgressBar();
                        Timber.e("Complete");
                    }
                });
    }

    @Override
    public void call(final Observable<Recipes> observable) {
        searchRecipes(observable);
    }
}
