package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.view.ViewSearch;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends MvpAppCompatFragment implements ViewSearch, RecyclerAdapter.Listener {

    @BindView(R.id.search_progressbar_progressbar)
    ProgressBar searchProgressBar;

    @BindView(R.id.search_list_recycler_view)
    RecyclerView listRecyclerView;

    @BindView(R.id.search_random_text_view)
    TextView searchText;

    @InjectPresenter
    SearchPresenter presenter;

    private RecyclerAdapter recyclerAdapter;
    private Bundle instanceState;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        instanceState = savedInstanceState;
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        final Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar_actionbar);
        ((MainActivity)getActivity()).setSupportActionBar(mActionBarToolbar);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listRecyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(this, new ArrayList<Hits>());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(listRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.devider));
        listRecyclerView.addItemDecoration(itemDecorator);
        listRecyclerView.setAdapter(recyclerAdapter);
        listRecyclerView.addOnScrollListener(new RecyclerViewMoreListener(listRecyclerView.getLayoutManager()) {
            @Override
            public void onScroll(final int totalItemCount) {
                presenter.callRandomUpdate(totalItemCount, String.valueOf(searchText.getText()));
            }
        });
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showList(final List<Hits> hitsList) {
        recyclerAdapter.updateAdapter(hitsList);
    }

    @Override
    public void updateList(final List<Hits> hitsList) {
        recyclerAdapter.updateList(hitsList);
    }

    @Override
    public void progressBarVisible(final int visible) {
        searchProgressBar.setVisibility(visible);
    }

    @Override
    public void setSearchText(final String text) {
        searchText.setText(text);
    }

    @Override
    public void setSnackBar() {
        Toast.makeText(getActivity(), getResources().getText(R.string.error_connection_api),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(final String uri) {
        ActivityManager.startCardActivity(getActivity(), uri);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_bar_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setFocusable(false);

        if (instanceState == null) {
            presenter.searchObservable(searchView);
        }
    }

    @Override
    public void addToFavorite(final Recipe recipe) {
        presenter.addToFavorite(recipe);
    }

    @Override
    public void deleteFromFavorite(final Recipe recipe) {
        presenter.deleteFromFavorite(recipe);
    }

}
