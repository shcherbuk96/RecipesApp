package com.example.stanislau_bushuk.foodhealth;

import com.example.stanislau_bushuk.foodhealth.Model.Recipes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Request {

    private static IAPI iapi;

    public static IAPI getIapi() {
        return iapi;
    }

    public void getJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iapi = retrofit.create(IAPI.class);

    }

    public interface IAPI {
        @GET("search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=50")
        Call<Recipes> getJson();
    }
}