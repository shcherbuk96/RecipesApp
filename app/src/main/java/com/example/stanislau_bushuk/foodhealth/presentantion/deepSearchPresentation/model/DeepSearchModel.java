package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model;

import android.widget.CompoundButton;

import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeepSearchModel {

    private int from = 0;
    private Map<String, Boolean> checkboxMap;
    private String calories;

    public DeepSearchModel() {
        checkboxMap = new HashMap<>();
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(final String calories) {
        this.calories = calories;
    }

    public void setMap(final CompoundButton compoundButton, final Boolean bool) {
        if (bool)
            checkboxMap.put(compoundButton.getTag().toString(), true);
        else checkboxMap.remove(compoundButton.getTag().toString());

    }

    public Map<String, String> getQueryMap() {
        final Map<String, String> query = new HashMap<>();

        for (final String key : checkboxMap.keySet()) {
            query.put(Constants.HEALTH, key);
        }

        return query;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(final int from) {
        this.from = from;
    }
}
