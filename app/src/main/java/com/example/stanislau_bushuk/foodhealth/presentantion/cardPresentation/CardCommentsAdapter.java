package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanislau_bushuk.foodhealth.R;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.modul.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardCommentsAdapter extends RecyclerView.Adapter<CardCommentsAdapter.MyViewHolder> {
    private final List<Comment> list;

    CardCommentsAdapter(final List<Comment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CardCommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_comments, parent, false);

        return new CardCommentsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardCommentsAdapter.MyViewHolder holder, final int position) {
        final Comment comment = list.get(position);

        holder.commentUser.setText(comment.getText());
        holder.emailUser.setText(comment.getEmail());

        GlideApp
                .with(holder.photoUser.getContext())
                .load(comment.getPhotoUri())
                .centerCrop()
                .placeholder(R.drawable.ic_person_black_24dp)
                .error(R.drawable.ic_person_black_24dp)
                .into(holder.photoUser);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(final List<Comment> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_item_user_comment_text_view)
        TextView commentUser;

        @BindView(R.id.card_item_user_photo_image_view)
        ImageView photoUser;

        @BindView(R.id.card_item_user_email_text_view)
        TextView emailUser;

        MyViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
