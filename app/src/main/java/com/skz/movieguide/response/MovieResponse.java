package com.skz.movieguide.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skz.movieguide.models.MovieModel;

//search for single movie
public class MovieResponse {
    // Finding the movie object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
