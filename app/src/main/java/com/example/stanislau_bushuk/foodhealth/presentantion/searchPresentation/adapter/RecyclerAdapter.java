package com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Hits;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Hits> hits;
    private Listener listener;

    public RecyclerAdapter(final Listener listener, final List<Hits> hits) {
        this.hits = hits;
        this.listener = listener;
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
                .with(holder.photoImageView.getContext())
                .load(recipe.getImage())
                .centerCrop()
                .into(holder.photoImageView);

        holder.starButton.setChecked(hits.get(position).getRecipe().isChecked());
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

    public interface Listener {
        void onItemClick(String uri);

        void addToFavorite(Recipe recipe);

        void deleteFromFavorite(Recipe recipe);

        void addToFb(Recipe recipe);

        void deleteFromFb(Recipe recipe);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title_text_view)
        TextView titleTextView;

        @BindView(R.id.item_photo_image_view)
        ImageView photoImageView;

        @BindView(R.id.item_linear_layout)
        LinearLayout linearLayout;

        @BindView(R.id.item_star_shine_button)
        CheckBox starButton;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    if (listener != null) {
                        listener.onItemClick(hits.get(getAdapterPosition()).getRecipe().getUri());
                    }

                }
            });

            starButton.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final CheckBox checkBox = (CheckBox) v;

                    if (checkBox.isChecked()) {
                        listener.addToFb(hits.get(getAdapterPosition()).getRecipe());
                    } else {
                        listener.deleteFromFb(hits.get(getAdapterPosition()).getRecipe());
                    }

                }
            });

            starButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                    if (listener != null) {

                        if (isChecked) {
                            buttonView.setButtonDrawable(R.drawable.ic_star_checked);
                            listener.addToFavorite(hits.get(getAdapterPosition()).getRecipe());
                        } else {
                            listener.deleteFromFavorite(hits.get(getAdapterPosition()).getRecipe());
                            buttonView.setButtonDrawable(R.drawable.ic_star);
                        }
                    }

                }
            });
        }
    }
}

