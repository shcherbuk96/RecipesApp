package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

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
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import timber.log.Timber;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private CallBackFavorite callBackFavorite;

    FavoriteAdapter(final CallBackFavorite callBackFavorite, final List<Recipe> list, final Context context) {
        this.recipeList = list;
        this.context = context;
        this.callBackFavorite = callBackFavorite;
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        return new FavoriteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.MyViewHolder holder, final int position) {
        final Recipe recipe = recipeList.get(position);

        if (recipe != null) {
            holder.starButton.setChecked(true);

            holder.starButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    callBackFavorite.deleteFromRealm(recipe);
                }
            });

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
                    ActivityManager.startCardActivity(context, recipe.getUri());
                }
            });
        }
    }

    public void updateAdapter(final RealmResults<Recipe> list) {
        recipeList.clear();
        recipeList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
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
