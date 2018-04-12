package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import timber.log.Timber;

public class RecyclerViewMoreListener extends RecyclerView.OnScrollListener {

    private OnLoadMoreListener loadMoreListener;
    private RecyclerView.LayoutManager layoutManager;

    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private String nameRecipe;

    RecyclerViewMoreListener(LinearLayoutManager layoutManager, OnLoadMoreListener loadMoreListener, String nameRecipe) {
        this.layoutManager = layoutManager;
        this.loadMoreListener = loadMoreListener;
        this.nameRecipe = nameRecipe;
    }


    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {

        if (loadMoreListener != null) {

            int totalItemCount = layoutManager.getItemCount();
            int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

            if (totalItemCount < previousTotalItemCount) {
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }

            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }


            Timber.e("last" + lastVisibleItemPosition + "total " + totalItemCount);
            if (!loading && (lastVisibleItemPosition + 1) == totalItemCount) {
                loadMoreListener.onLoadMore(totalItemCount, nameRecipe);
                loading = true;
            }
        }
    }


    public interface OnLoadMoreListener {
        void onLoadMore(int count, String nameRecipe);
    }
}

