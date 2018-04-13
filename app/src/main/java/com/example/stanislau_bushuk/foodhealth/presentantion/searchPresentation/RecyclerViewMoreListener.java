package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

abstract class RecyclerViewMoreListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;

    private int previousTotalItemCount = 0;
    private boolean loading = true;

    RecyclerViewMoreListener(final RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrolled(final RecyclerView view, final int dx, final int dy) {


        final int totalItemCount = layoutManager.getItemCount();
        final int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

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

        if (!loading && (lastVisibleItemPosition + 1) == totalItemCount) {
            onScroll(totalItemCount);
            loading = true;
        }

    }

    public abstract void onScroll(int totalItemCount);

}

