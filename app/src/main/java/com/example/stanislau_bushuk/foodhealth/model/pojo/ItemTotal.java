package com.example.stanislau_bushuk.foodhealth.model.pojo;

public class ItemTotal {
    private String label;
    private float quantity;
    private String unit;

    public static class Builder{
        private float quantity;
        private String unit="g";
        private String label="name";

        public Builder(final float quantity){
            this.quantity=quantity;
        }

        public Builder unit(final String val){
            unit=val;
            return this;
        }

        public Builder label(final String val){
            label=val;
            return this;
        }

        public ItemTotal buidl(){
            return new ItemTotal(this);
        }
    }

    public ItemTotal(final Builder builder){
        label=builder.label;
        quantity=builder.quantity;
        unit=builder.unit;
    }

    public String getLabel() {
        return label;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }


}