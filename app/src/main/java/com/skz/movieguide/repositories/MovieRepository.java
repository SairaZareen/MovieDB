package com.skz.movieguide.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skz.movieguide.models.MovieModel;
import com.skz.movieguide.request.MovieApiClient;
import com.skz.movieguide.utils.MovieApi;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getPopular(){
        return movieApiClient.getPop();
    }
    //2-call search method in repository
    public void searchMovieApi(String query,int pageNumber){
        mQuery = query;
        mPageNumber = pageNumber;
    movieApiClient.searchMoviesApi(query,pageNumber);
    }

    //Popular movies
    public void searchMoviePopular(int pageNumber){

        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);
    }
    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }
}
