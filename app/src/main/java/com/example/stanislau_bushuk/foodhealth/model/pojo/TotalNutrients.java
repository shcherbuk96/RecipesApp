package com.example.stanislau_bushuk.foodhealth.model.pojo;

import io.realm.RealmObject;

public class TotalNutrients extends RealmObject {
    private ItemTotal FAT;
    private ItemTotal CHOCDF;
    private ItemTotal PROCNT;


    public ItemTotal getFAT() {
        return FAT;
    }

    public void setFAT(final ItemTotal FAT) {
        this.FAT = FAT;
    }

    public ItemTotal getCHOCDF() {
        return CHOCDF;
    }

    public void setCHOCDF(final ItemTotal CHOCDF) {
        this.CHOCDF = CHOCDF;
    }

    public ItemTotal getPROCNT() {
        return PROCNT;
    }

    public void setPROCNT(final ItemTotal PROCNT) {
        this.PROCNT = PROCNT;
    }
}
