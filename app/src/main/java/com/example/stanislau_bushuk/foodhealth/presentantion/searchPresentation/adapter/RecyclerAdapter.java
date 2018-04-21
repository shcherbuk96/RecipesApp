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
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.CallBackFavorite;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Hits> hits;

    private CallBackFavorite callBackFavorite;

    public RecyclerAdapter(final CallBackFavorite callBackFavorite, final List<Hits> hits, final Context context) {
        this.hits = hits;
        this.context = context;
        this.callBackFavorite = callBackFavorite;
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

        holder.starButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final View view, final boolean checked) {
                if (checked) {
                    callBackFavorite.addToRealm(recipe);
                    //realmModel.addDataToRealm(recipe);
                } else {
                    callBackFavorite.deleteFromRealm(recipe);
                }
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Timber.e(recipe.getUri());
                ActivityManager.startCardActivity(context, recipe.getUri());
            }
        });
       /* if (realmModel.checkFavorite(recipe)) {
            holder.starButton.setChecked(true);
            Timber.e(String.valueOf(position));
        }*/
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

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title_text_view)
        TextView titleTextView;

        @BindView(R.id.item_photo_image_view)
        ImageView photoImageView;

        @BindView(R.id.item_linear_layout)
        LinearLayout linearLayout;

        @BindView(R.id.item_star_shine_button)
        ShineButton starButton;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

