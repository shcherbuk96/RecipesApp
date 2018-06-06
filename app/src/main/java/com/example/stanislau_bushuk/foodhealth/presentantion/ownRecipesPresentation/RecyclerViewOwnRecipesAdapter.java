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
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RecyclerViewOwnRecipesAdapter extends RecyclerView.Adapter<RecyclerViewOwnRecipesAdapter.MyViewHolder> {

    private List<OwnRecipe> ownRecipes;
    private Listener listener;

    public RecyclerViewOwnRecipesAdapter(final Listener listener, final List<OwnRecipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
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
        final OwnRecipe recipe = ownRecipes.get(position);
        holder.titleTextView.setText(recipe.getRecipeName());
        GlideApp
                .with(holder.photoImageView.getContext())
                .load(recipe.getRecipePhoto())
                .error(R.drawable.insert_own_recipe_photo)
                .centerCrop()
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return ownRecipes.size();
    }

    public void updateAdapter(final List<OwnRecipe> hits) {
        this.ownRecipes.clear();
        Timber.e(String.valueOf(hits.size()));
        this.ownRecipes.addAll(hits);
        notifyDataSetChanged();
    }

    public void addRecipe(final OwnRecipe recipe){
        this.ownRecipes.add(recipe);
        notifyItemInserted(getItemCount());
    }


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
