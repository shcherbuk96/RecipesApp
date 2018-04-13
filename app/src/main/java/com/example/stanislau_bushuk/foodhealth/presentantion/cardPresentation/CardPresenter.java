package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.ItemTotal;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
                .map(new Function<List<Recipe>, Recipe>() {
                    @Override
                    public Recipe apply(final List<Recipe> recipeList) throws Exception {
                        final Recipe recipe = recipeList.get(0);

                        if (recipe != null) {

                            if (recipe.getTotalNutrients().getFAT() == null) {
                                recipe.getTotalNutrients().setFAT(new ItemTotal.Builder(0).buidl());
                            }

                            if (recipe.getTotalNutrients().getPROCNT() == null) {
                                recipe.getTotalNutrients().setPROCNT(new ItemTotal.Builder(0).buidl());
                            }

                            if (recipe.getTotalNutrients().getCHOCDF() == null) {
                                recipe.getTotalNutrients().setCHOCDF(new ItemTotal.Builder(0).buidl());
                            }
                        }

                        return recipe;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recipe>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("onSubscribe");
                    }

                    @Override
                    public void onNext(final Recipe recipe) {
                        Timber.e("onNext");
                        getViewState().showList(recipe);
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
