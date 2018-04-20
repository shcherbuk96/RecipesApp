package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class DeepSearchModel {

    private Map<String, Boolean> checkboxMap = new HashMap<String, Boolean>();

    public DeepSearchModel() {
        checkboxMap = new HashMap<String, Boolean>();
    }

    public void setMap(final CompoundButton compoundButton, final Boolean bool){
        Timber.e(compoundButton.getTag().toString());
        if(bool)
            checkboxMap.put(compoundButton.getTag().toString(), true);
        else checkboxMap.remove(compoundButton.getTag().toString());
    }

    public Map<String,String> getQueryMap(){
        final Map<String,String> query = new HashMap<String, String>();
        for (final String key : checkboxMap.keySet()) {
            query.put("Health",key);
        }
        return query;
    }

}
