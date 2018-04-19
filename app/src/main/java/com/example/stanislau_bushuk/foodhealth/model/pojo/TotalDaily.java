package com.example.stanislau_bushuk.foodhealth.model.pojo;

import io.realm.RealmObject;

public class TotalDaily extends RealmObject {
    private ItemTotal ENERC_KCAL;

    public ItemTotal getENERC_KCAL() {
        return ENERC_KCAL;
    }

    public void setENERC_KCAL(final ItemTotal ENERC_KCAL) {
        this.ENERC_KCAL = ENERC_KCAL;
    }
}
