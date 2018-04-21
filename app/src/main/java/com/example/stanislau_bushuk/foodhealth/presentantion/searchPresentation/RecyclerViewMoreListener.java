package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stanislau_bushuk.foodhealth.Constants;

import timber.log.Timber;

public abstract class RecyclerViewMoreListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    public RecyclerViewMoreListener(final RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(final RecyclerView view, final int dx, final int dy) {
        final int totalItemCount = layoutManager.getItemCount();
        final int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }


        if (!loading && (lastVisibleItemPosition + Constants.HALF_ITEMS_IN_PAGE) >= previousTotalItemCount) {//на анонимном классе не сделаешь нормальное обновление, нужен кол бек из презентера, что реквест не прошёл
            onScroll(totalItemCount);
            loading = true;
        }
    }

    public abstract void onScroll(int totalItemCount);

}

