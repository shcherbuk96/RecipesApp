package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

public class EditData {
    private float fat;
    private float prot;
    private float chocdf;
    private float ENERC_KCAL;
    private float calories;
    private float yield;

    public static Builder newBuilder() {
        return new EditData().new Builder();
    }

    public float getFat() {
        return fat;
    }

    public float getProt() {
        return prot;
    }

    public float getChocdf() {
        return chocdf;
    }

    public float getENERC_KCAL() {
        return ENERC_KCAL;
    }

    public float getCalories() {
        return calories;
    }

    public float getYield() {
        return yield;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setENERC_KCAL(final float quanity) {
            ENERC_KCAL = quanity;

            return this;
        }

        public Builder setCalories(final float quanity) {
            calories = quanity;

            return this;
        }

        public Builder setYield(final float quanity) {
            yield = quanity;

            return this;
        }

        public Builder setFat(final float quanity) {
            fat = quanity;

            return this;
        }

        public Builder setProt(final float quanity) {
            prot = quanity;

            return this;
        }

        public Builder setChocdf(final float quanity) {
            chocdf = quanity;

            return this;
        }

        public EditData build() {
            return EditData.this;
        }
    }
}
