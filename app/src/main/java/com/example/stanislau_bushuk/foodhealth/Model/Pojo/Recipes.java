package com.example.stanislau_bushuk.foodhealth.Model.Pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }


    public void setTo(Integer to) {
        this.to = to;
    }





    public void setParams(ArrayList params) {
        this.params = params;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }


    public void setHits(ArrayList<Hits> hits) {
        this.hits = hits;
    }

}
