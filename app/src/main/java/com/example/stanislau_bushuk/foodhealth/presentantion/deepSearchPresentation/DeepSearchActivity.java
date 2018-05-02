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
import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.view.DeepSearchView;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.RecyclerViewMoreListener;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DeepSearchActivity extends MvpAppCompatActivity implements DeepSearchView, RecyclerAdapter.Listener {

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

        App.getAppComponent().inject(this);
        setContentView(R.layout.activity_deep_search);
        ButterKnife.bind(this);
        adapter = new RecyclerAdapter(this, new ArrayList<Hits>());
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
    public void notFound() {
        Toast.makeText(this, getResources().getString(R.string.deep_search_not_found), Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearViewStateCommands() {
        Timber.e("clear");
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {
            presenter.setStartFrom();
            presenter.getViewState().clearViewStateCommands();
            super.onBackPressed();
        }

        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onBackPressed() {
        presenter.setStartFrom();
        presenter.getViewState().clearViewStateCommands();
        super.onBackPressed();
    }

    @Override
    public void onItemClick(final String uri) {
        ActivityManager.startCardActivity(this,uri);
    }

    @Override
    public void addToFavorite(final Recipe recipe) {

    }

    @Override
    public void deleteFromFavorite(final Recipe recipe) {

    }
}
