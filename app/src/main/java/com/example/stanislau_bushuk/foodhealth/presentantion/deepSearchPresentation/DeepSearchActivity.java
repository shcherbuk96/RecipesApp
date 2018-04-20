package com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
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
    private ArrayList<Hits> recipes;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_search);

        ButterKnife.bind(this);
        presenter.attachView(this);
        Timber.e(presenter.getAttachedViews().toString());
        recipes = new ArrayList<>();
        adapter = new RecyclerAdapter(recipes, this);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showData(final ArrayList<Hits> recipes) {

        if (recipes != null && recipes.size() != 0) {
            this.recipes.clear();
            this.recipes.addAll(recipes);
        }else{
            Toast.makeText(this,"Cant find this",Toast.LENGTH_LONG).show();
        }

        adapter.updateAdapter(recipes);
    }

    @Override
    public void progressBarVisibility(final int visible) {
        progressBar.setVisibility(visible);
    }
}
