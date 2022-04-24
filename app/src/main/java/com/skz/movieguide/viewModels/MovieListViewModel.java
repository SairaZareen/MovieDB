package com.skz.movieguide.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skz.movieguide.models.MovieModel;
import com.skz.movieguide.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

   private MovieRepository movieRepository;

    //Constructor
    public MovieListViewModel(){
    movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public LiveData<List<MovieModel>> getMoviesPopular(){
        return movieRepository.getPopular();
    }

    //3- call search in viewmodel
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }

    //Popular movies
    public void searchMoviePopular(int pageNumber){
        movieRepository.searchMoviePopular(pageNumber);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }

}
