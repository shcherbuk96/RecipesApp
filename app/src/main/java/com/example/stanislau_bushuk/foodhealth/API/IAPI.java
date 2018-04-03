package com.example.stanislau_bushuk.foodhealth.API;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface IAPI {

    @GET
    public void getJson(@Url String url);//send url fo request

    //sample of url
    //https://api.edamam.com/search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=50
}
