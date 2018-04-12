package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> implements CallBackCardPresenter {

    @Inject
    CardNetWorkModel netWorkModel;

    CardPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackCard(this);
    }

    public void getRecipeFromUri(final String uri) {
        netWorkModel.getRecipeFromUri(uri);
    }

    @Override
    public void call(final Observable<List<Recipe>> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Recipe>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("onSubscribe");
                    }

                    @Override
                    public void onNext(final List<Recipe> recipeList) {
                        Timber.e("onNext");

                        if (recipeList != null) {
                            final Recipe recipe = recipeList.get(0);
                            if (recipe.getTotalNutrients().getFAT() == null) {
                                recipe.getTotalNutrients().getFAT().setQuantity(0);
                            }

                            if (recipe.getTotalNutrients().getPROCNT() == null) {
                                recipe.getTotalNutrients().getPROCNT().setQuantity(0);
                            }

                            if (recipe.getTotalNutrients().getCHOCDF() == null) {
                                recipe.getTotalNutrients().getCHOCDF().setQuantity(0);
                            }

                            getViewState().showList(recipe);
                        }

                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("onComplete");
                    }
                });
    }
}
