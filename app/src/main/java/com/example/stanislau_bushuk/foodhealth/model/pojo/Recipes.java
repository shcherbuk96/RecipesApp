package com.example.stanislau_bushuk.foodhealth.model.pojo;

import java.util.ArrayList;

public class Recipes {

    private String q;
    private int from;
    private int to;
    private Object params;
    private boolean more;
    private int count;
    private ArrayList<Hits> hits;


    public String getQ() {
        return q;
    }

    public void setQ(final String q) {
        this.q = q;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(final int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(final int to) {
        this.to = to;
    }

    public void setParams(final ArrayList params) {
        this.params = params;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(final Boolean more) {
        this.more = more;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    public void setHits(final ArrayList<Hits> hits) {
        this.hits = hits;
    }

}
