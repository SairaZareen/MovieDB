package com.skz.movieguide.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skz.movieguide.R;

import java.awt.font.TextAttribute;

public class PopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    ImageView imageView2;
    RatingBar ratingBar2;
    ImageView favoriteButton;

    //Click Listener
    OnMovieClickListener onMovieClickListener;


    public PopularViewHolder(@NonNull View itemView,OnMovieClickListener onMovieClickListener) {
        super(itemView);

        this.onMovieClickListener = onMovieClickListener;

        imageView2 = itemView.findViewById(R.id.movie_img_popular);
        ratingBar2 = itemView.findViewById(R.id.rating_bar_pop);
        favoriteButton = itemView.findViewById(R.id.fav_btn);

        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        onMovieClickListener.onMovieClick(getAdapterPosition());

    }
}
