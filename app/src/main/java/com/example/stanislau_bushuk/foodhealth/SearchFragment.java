package com.example.stanislau_bushuk.foodhealth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.stanislau_bushuk.foodhealth.Adapter.RecyclerAdapter;

import butterknife.BindView;


public class SearchFragment extends MvpAppCompatFragment {
    @BindView(R.id.list_search)
    RecyclerView recyclerView;

    RecyclerAdapter recyclerAdapter;

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
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        setAdapter();
        return view;
    }

    public void setAdapter(){
       // recyclerAdapter = new RecyclerAdapter(list, getContext());

        recyclerView.setAdapter(recyclerAdapter);
    }




}
