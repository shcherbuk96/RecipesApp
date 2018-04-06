package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.Pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.Pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Hits> hits;


    public RecyclerAdapter(final List<Hits> hits, final Context context) {
        this.hits = hits;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Recipe recipe = hits.get(position).getRecipe();
        holder.title.setText(recipe.getLabel());
        recipe = hits.get(position).getRecipe();
        holder.titleTextView.setText(recipe.getLabel());
        GlideApp
                .with(context)
                .load(recipe.getImage())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return hits.size();

    }

    public void updateAdapter(final List<Hits> hits) {
        if (hits.size() != 0) {
            this.hits.clear();
            this.hits.addAll(hits);
            notifyDataSetChanged();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_image)
        ImageView image;
        //RelativeLayout relative;

        MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

