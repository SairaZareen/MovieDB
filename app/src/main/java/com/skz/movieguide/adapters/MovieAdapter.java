package com.skz.movieguide.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.skz.movieguide.R;
import com.skz.movieguide.models.MovieModel;
import com.skz.movieguide.utils.Credentials;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieClickListener onMovieClickListener;

    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 2;

    public MovieAdapter(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == DISPLAY_SEARCH){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
            return new MovieViewHolder(view, onMovieClickListener);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movies_layout,parent,false);
            return new PopularViewHolder(view, onMovieClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == DISPLAY_SEARCH){
            //since our rating is out of 5 divide by 2
            ((MovieViewHolder)holder).ratingBar.setRating(mMovies.get(i).getVote_average()/2);

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(i).getPoster_path())
                    .into(((MovieViewHolder)holder).imageView);
        }else {
            //since our rating is out of 5 divide by 2
            ((PopularViewHolder)holder).ratingBar2.setRating(mMovies.get(i).getVote_average()/2);

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(i).getPoster_path())
                    .into(((PopularViewHolder)holder).imageView2);
        }

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

    @Override
    public int getItemViewType(int position) {

        if (Credentials.POPULAR){
            return DISPLAY_POP;
        }
        else
            return DISPLAY_SEARCH;
    }
}
