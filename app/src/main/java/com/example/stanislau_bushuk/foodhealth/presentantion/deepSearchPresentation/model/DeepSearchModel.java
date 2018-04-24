package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model;

import android.widget.CompoundButton;

import com.example.stanislau_bushuk.foodhealth.Constants;

import java.util.HashMap;
import java.util.Map;

public class DeepSearchModel {

    private int from = 0;
    private Map<String, String> checkboxMap;
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

    public void setMap(final CompoundButton compoundButton, final Boolean bool, final String label) {

        if (bool) {
            checkboxMap.put(label,compoundButton.getTag().toString());
        } else {
            checkboxMap.remove(compoundButton.getTag().toString());
        }

    }

    public Map<String, String> getQueryMap() {
        return checkboxMap;
    }


    public int getFrom() {
        return from;
    }

    public void setFrom(final int from) {
        this.from = from;
    }
}
