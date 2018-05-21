package com.example.stanislau_bushuk.foodhealth.api;


import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IAPI {

    @GET("search")
    Observable<Recipes> getRecipeWithName(@Query("q") String q, @Query("app_id") String app_id,
                                          @Query("app_key") String app_key, @Query("from") String from,
                                          @Query("to") String to);

    @GET("search")
    Observable<Recipes> getRandomRecipe(@Query("q") String q, @Query("app_id") String app_id,
                                        @Query("app_key") String app_key, @Query("from") String from,
                                        @Query("to") String to, @Query("calories") String calories);


    @GET("search")
    Observable<List<Recipe>> getRecipeWithUri(@Query("r") String r, @Query("app_id") String app_id,
                                              @Query("app_key") String app_key);

    @GET("search")
    Observable<Recipes> getRecipeFilter(@Query("q") String q, @Query("app_id") String app_id,
                                        @Query("app_key") String app_key, @Query("from") String from,
                                        @Query("to") String to, @Query("calories") String calories,
                                        @QueryMap Map<String, String> health);

    @GET("search")
    Observable<Recipes> getRecipeFilterCountIngredients(@Query("q") String q, @Query("app_id") String app_id,
                                                        @Query("app_key") String app_key,
                                                        @Query("from") String from,
                                                        @Query("to") String to, @Query("calories") String calories,
                                                        @Query("ingr") String countIngredients,
                                                        @QueryMap Map<String, String> health);

}
