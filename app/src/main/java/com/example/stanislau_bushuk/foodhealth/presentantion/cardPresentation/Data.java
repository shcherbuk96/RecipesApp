package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.example.stanislau_bushuk.foodhealth.model.pojo.ItemTotal;
import com.example.stanislau_bushuk.foodhealth.model.pojo.TotalNutrients;

public class Data {
    private ItemTotal fat;
    private ItemTotal prot;
    private ItemTotal chocdf;

    public static Builder newBuilder() {
        return new Data().new Builder();
    }

    public ItemTotal getFat() {
        return fat;
    }

    public ItemTotal getProt() {
        return prot;
    }

    public ItemTotal getChocdf() {
        return chocdf;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setFat(final TotalNutrients totalNutrients) {
            if (totalNutrients.getFAT() == null) {
                fat = new ItemTotal();
            } else {
                fat = totalNutrients.getFAT();
            }

            return this;
        }

        public Builder setProt(final TotalNutrients totalNutrients) {
            if (totalNutrients.getPROCNT() == null) {
                prot = new ItemTotal();
            } else {
                prot = totalNutrients.getPROCNT();
            }

            return this;
        }

        public Builder setChocdf(final TotalNutrients totalNutrients) {
            if (totalNutrients.getCHOCDF() == null) {
                chocdf = new ItemTotal();
            } else {
                chocdf = totalNutrients.getCHOCDF();
            }

            return this;
        }

        public Data build() {
            return Data.this;
        }
    }
}
