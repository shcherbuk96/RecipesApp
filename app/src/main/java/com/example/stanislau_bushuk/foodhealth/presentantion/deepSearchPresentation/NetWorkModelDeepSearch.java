package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.api.IAPI;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by logoped583st on 20.4.18.
 */

public class NetWorkModelDeepSearch {

    @Inject
    IAPI iapi;

    private DeepSearchPresenter presenter;

    public NetWorkModelDeepSearch() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(final DeepSearchPresenter presenter) {
        this.presenter = presenter;
    }

    public void getRecipeFilter(final String calories, final int from, final Map<String, String> query) {
        final Observable<Recipes> observable = iapi.getRecipeFilter(Constants.RANDOM_RECIPE, Constants.APP_ID, Constants.APP_KEY,
                String.valueOf(from), String.valueOf(from + Constants.ITEMS_IN_PAGE), calories, query);
        presenter.callBack(observable);
    }

}
