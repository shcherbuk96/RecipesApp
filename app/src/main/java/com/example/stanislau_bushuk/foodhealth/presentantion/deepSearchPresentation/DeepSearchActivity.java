package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.RecyclerViewMoreListener;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DeepSearchActivity extends MvpAppCompatActivity implements DeepSearchView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    DeepSearchPresenter presenter;

    @BindView(R.id.recycler_deep_search)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar_deep_search)
    ProgressBar progressBar;

    private RecyclerAdapter adapter;
    private boolean instanceState=false;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null){
            instanceState=true;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        setContentView(R.layout.activity_deep_search);
        ButterKnife.bind(this);
        adapter = new RecyclerAdapter(new ArrayList<Hits>(), this);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerViewMoreListener(layoutManager) {
            @Override
            public void onScroll(final int totalItemCount) {
                presenter.updateRecipeFilter();
            }
        });

    }

    @Override
    public void showData(final ArrayList<Hits> recipes) {
        adapter.updateList(recipes);
    }


    @Override
    public void progressBarVisibility(final int visible) {
        progressBar.setVisibility(visible);
    }

    @Override
    public void clearAdapter() {

        if(adapter.getItemCount()>0 && !instanceState){
            instanceState=true;
            Timber.e("clear");
            adapter.clearAdapter();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.model.setFrom(0);
        onBackPressed();

        return true;
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
