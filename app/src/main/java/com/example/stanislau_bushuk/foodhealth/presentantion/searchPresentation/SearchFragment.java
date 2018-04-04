package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.Adapter.RecyclerAdapter;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.Presenters.SearchPresenter;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.View.ViewSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends MvpAppCompatFragment implements ViewSearch {
    @BindView(R.id.list_search)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectPresenter
    SearchPresenter presenter;
    RecyclerAdapter recyclerAdapter;
    private ArrayList<Hits> hitsList;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        hitsList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(hitsList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        return view;
    }


    @Override
    public void showList(List<Hits> hitsList) {
        if (hitsList != null) {
            this.hitsList.clear();
            this.hitsList.addAll(hitsList);
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


}
