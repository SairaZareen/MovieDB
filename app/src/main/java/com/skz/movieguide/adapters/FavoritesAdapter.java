package com.skz.movieguide.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.skz.movieguide.R;
import com.skz.movieguide.models.MovieModel;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MovieModel> mMovies;
    private OnMovieClickListener onMovieClickListener;

    public FavoritesAdapter(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FavoriteViewHolder)holder).ratingBar.setRating(mMovies.get(position).getVote_average()/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(position).getPoster_path())
                .into(((FavoriteViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null){
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    //getting id of the movie clicked
    public MovieModel getSelectedMovie(int position){
        if (mMovies != null){
            if (mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return null;
    }
}
