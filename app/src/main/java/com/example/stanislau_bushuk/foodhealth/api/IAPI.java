package com.example.stanislau_bushuk.foodhealth.api;


import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAPI {

    @GET("search")
    Observable<Recipes> getRecipeWithName(@Query("q") String q, @Query("app_id") String app_id, @Query("app_key") String app_key, @Query("from") String from, @Query("to") String to);

    @GET("search")
    Observable<Recipes> getRandomRecipe(@Query("q") String q, @Query("app_id") String app_id, @Query("app_key") String app_key, @Query("from") String from,
                                        @Query("to") String to, @Query("calories") String calories);


    @GET("search")
    Observable<List<Recipe>> getRecipeWithUri(@Query("r") String r, @Query("app_id") String app_id, @Query("app_key") String app_key);

    //sample of url
    //http://www.edamam.com/ontologies/edamam.owl#recipe_ef501809e5dd1a4cf72c650b04ffca96
    //https://api.edamam.com/search?q=chicken&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658&from=0&to=50
    //https://api.edamam.com/search?r=http://www.edamam.com/ontologies/edamam.owl#recipe_ef501809e5dd1a4cf72c650b04ffca96&app_id=8fe07cd3&app_key=d0f2fdfa54e4a68a2f8d96a0e34a7658
}
