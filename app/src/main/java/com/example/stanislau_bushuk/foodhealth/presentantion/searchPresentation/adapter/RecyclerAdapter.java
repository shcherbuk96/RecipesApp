package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Hits> hits;


    public RecyclerAdapter(final List<Hits> hits, final Context context) {
        this.hits = hits;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Recipe recipe = hits.get(position).getRecipe();
        holder.titleTextView.setText(recipe.getLabel());
        GlideApp
                .with(context)
                .load(recipe.getImage())
                .centerCrop()
                .into(holder.photoImageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Timber.e(recipe.getUri());
                ActivityManager.startCardActivity(context,recipe.getUri());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public void updateAdapter(final List<Hits> hits) {

        if (hits != null && hits.size() != 0) {
            this.hits.clear();
            this.hits.addAll(hits);
            notifyDataSetChanged();
        }

    }

    public void updateList(final List<Hits> hits) {

        if (hits != null) {
            this.hits.addAll(hits);
            notifyDataSetChanged();
        }

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title_text_view)
        TextView titleTextView;

        @BindView(R.id.item_photo_image_view)
        ImageView photoImageView;

        @BindView(R.id.item_linear_layout)
        LinearLayout linearLayout;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

