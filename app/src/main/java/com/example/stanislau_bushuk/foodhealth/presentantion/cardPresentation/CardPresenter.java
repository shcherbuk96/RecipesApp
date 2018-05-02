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

    public void getEditData(final float number, final Data data) {
        final EditData editData = EditData.newBuilder()
                .setCalories(data.getCalories() * number / data.getYield())
                .setENERC_KCAL(data.getENERC_KCAL().getQuantity() * number / data.getYield())
                .setChocdf(data.getChocdf().getQuantity() * number / data.getYield())
                .setFat(data.getFat().getQuantity() * number / data.getYield())
                .setProt(data.getProt().getQuantity() * number / data.getYield())
                .setYield(data.getYield())
                .build();
        getViewState().showEditData(editData);
    }

    @Override
    public void call(final Observable<List<Recipe>> observable) {
        observable.subscribeOn(Schedulers.io())
                .map(new Function<List<Recipe>, Data>() {
                    @Override
                    public Data apply(final List<Recipe> recipeList) {
                        final Recipe recipe = recipeList.get(0);

                        return Data.newBuilder()
                                .setFat(recipe.getTotalNutrients())
                                .setProt(recipe.getTotalNutrients())
                                .setChocdf(recipe.getTotalNutrients())
                                .setCalories(recipe)
                                .setYield(recipe)
                                .setENERC_KCAL(recipe.getTotalDaily())
                                .setLabel(recipe)
                                .setImage(recipe)
                                .setIngridients(recipe)
                                .build();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("onSubscribe");
                    }

                    @Override
                    public void onNext(final Data data) {
                        Timber.e("onNext");
                        getViewState().showList(data);
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
