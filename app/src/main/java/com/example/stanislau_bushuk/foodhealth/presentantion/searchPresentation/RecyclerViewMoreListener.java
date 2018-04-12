package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import timber.log.Timber;

 abstract class RecyclerViewMoreListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;

    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private String nameRecipe;

    RecyclerViewMoreListener(final LinearLayoutManager layoutManager, final String nameRecipe) {
        this.layoutManager = layoutManager;
        this.nameRecipe = nameRecipe;
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

            Timber.e("last" + lastVisibleItemPosition + "total " + totalItemCount);
            if (!loading && (lastVisibleItemPosition + 1) == totalItemCount) {
                onScroll(totalItemCount,nameRecipe);
                loading = true;
            }
        }

     public abstract void onScroll(int totalItemCount,String nameRecipe);

 }

