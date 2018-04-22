package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.view.DeepSearchView;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.RecyclerViewMoreListener;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeepSearchActivity extends MvpAppCompatActivity implements DeepSearchView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    DeepSearchPresenter presenter;

    @BindView(R.id.recycler_deep_search)
    RecyclerView recyclerView;

    @BindView(R.id.progressbar_deep_search)
    ProgressBar progressBar;

    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        presenter.attachView(this);
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
    public void notFound() {
        Toast.makeText(this, Constants.NOTFOUND, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        presenter.model.setFrom(0);
        presenter.model.getRecipes().clear();

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case android.R.id.home:
                presenter.model.setFrom(0);
                presenter.model.getRecipes().clear();

                super.onBackPressed();
                return true;
        }

        return (super.onOptionsItemSelected(menuItem));
    }


    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
