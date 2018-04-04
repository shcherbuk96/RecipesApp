package com.example.stanislau_bushuk.foodhealth.API;


import com.example.stanislau_bushuk.foodhealth.Model.Recipes;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPI {

    @GET("search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=50")
    Observable<Recipes> getJson();

    //sample of url
    //https://api.edamam.com/search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=50
}
