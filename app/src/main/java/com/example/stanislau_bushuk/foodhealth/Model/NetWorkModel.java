package com.example.stanislau_bushuk.foodhealth.Model;

import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constats;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipes;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.Presenters.SearchPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

import static com.example.stanislau_bushuk.foodhealth.Constats.APP_ID;
import static com.example.stanislau_bushuk.foodhealth.Constats.APP_KEY;

public class NetWorkModel {

    @Inject
    IAPI iapi;
    private CallBackSearchPresenter callBackSearchPresenter;

    public NetWorkModel() {
        App.getAppComponent().inject(this);
    }

    public void setCallBack(SearchPresenter presenter) {
        callBackSearchPresenter = presenter;
        getResponse();
    }

    public void getResponse(){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("to","10");
        hashMap.put("from","0");
        hashMap.put("app_key","d0f2fdfa54e4a68a2f8d96a0e34a7658");
        hashMap.put("app_id","8fe07cd3");
        hashMap.put("q","chiken");
        Timber.e(hashMap.toString());
        Observable<Recipes> observable = iapi.getJson("chiken","8fe07cd3","d0f2fdfa54e4a68a2f8d96a0e34a7658","0","10");
        //Observable<Recipes> observable = iapi.getJson("https://api.edamam.com/search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=10");
        //https://api.edamam.com/search?q=chiken&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=10&app_id=8fe07cd3
        callBackSearchPresenter.call(observable);
    }

    public class RequestBody{
        public String q="chiken";
        public String app_id = "8fe07cd3";
        public String app_key = "d0f2fdfa54e4a68a2f8d96a0e34a7658";
        public String from="0";
        public String to = "10";
    }

}
