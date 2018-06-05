package com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.OwnRecipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewOwnRecipesAdapter extends RecyclerView.Adapter<RecyclerViewOwnRecipesAdapter.MyViewHolder> {

    private List<OwnRecipe> hits;
    private Listener listener;

    public RecyclerViewOwnRecipesAdapter(final Listener listener, final List<OwnRecipe> hits) {
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
//        final Recipe recipe = hits.get(position).getRecipe();
//        holder.titleTextView.setText(recipe.getLabel());
//        GlideApp
//                .with(holder.photoImageView.getContext())
//                .load(recipe.getImage())
//                .centerCrop()
//                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

//    public void updateAdapter(final List<Hits> hits) {
//        if (hits != null && hits.size() != 0) {
//            this.hits.clear();
//            this.hits.addAll(hits);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void updateList(final List<Hits> hits) {
//        if (hits != null) {
//            this.hits.addAll(hits);
//            notifyDataSetChanged();
//        }
//    }

    public interface Listener {
        void onItemClick(String uri);
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
