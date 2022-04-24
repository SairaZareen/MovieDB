package com.skz.movieguide.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skz.movieguide.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView imageView;
    RatingBar ratingBar;

    //Click Listener
    OnMovieClickListener onMovieClickListener ;


    public FavoriteViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
        super(itemView);

        this.onMovieClickListener = onMovieClickListener;

        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        onMovieClickListener.onMovieClick(getAdapterPosition());

    }
}
