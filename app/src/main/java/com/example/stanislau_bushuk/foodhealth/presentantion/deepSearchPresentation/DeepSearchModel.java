package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.Map;

public class DeepSearchModel {

    private Map<CompoundButton, Boolean> checkboxMap = new HashMap<CompoundButton, Boolean>();

    public DeepSearchModel() {
        checkboxMap = new HashMap<CompoundButton, Boolean>();
    }

    public void setMap(final CompoundButton compoundButton, final Boolean bool){
        if(bool)
            checkboxMap.put(compoundButton, true);
        else checkboxMap.remove(compoundButton);
    }
}
