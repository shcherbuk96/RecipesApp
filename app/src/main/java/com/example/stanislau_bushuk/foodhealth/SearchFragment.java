package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;


public class SearchFragment extends MvpAppCompatFragment {
    @BindView(R.id.list_search)
    RecyclerView recyclerView;

    //RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search2, container, false);

        return view;
    }

    public void setAdapter(){
       /* recyclerAdapter = new RecyclerAdapter(list, getContext());

*//*        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);*//*
        recyclerView.setAdapter(recyclerAdapter);*/
    }




}
