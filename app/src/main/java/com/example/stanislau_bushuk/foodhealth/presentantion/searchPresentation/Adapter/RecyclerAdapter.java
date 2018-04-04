package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.Model.Pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.Modul.GlideApp;
import com.example.stanislau_bushuk.foodhealth.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Hits> hits;


    public RecyclerAdapter(List<Hits> hits, Context context) {
        this.hits = hits;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Recipe recipe = hits.get(position).getRecipe();
        holder.title.setText(recipe.getLabel());
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_image)
        ImageView image;
        //RelativeLayout relative;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

