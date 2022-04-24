package com.skz.movieguide.utils;

import com.skz.movieguide.models.MovieModel;
import com.skz.movieguide.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//query for single movie :-
//https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

//query for popular movies acc to page no. :-
//https://api.themoviedb.org/3/movie/popular?api_key=6888944308dcb609c0c270d20f20ca11&page=2

public interface MovieApi {

    //Search for movies
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key,@Query("query") String query, @Query("page") int page);

    //making search with ID
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    //Popular movies
    // https://api.themoviedb.org/3/movie/popular?api_key=52a18783ed514602a5facb15a0177e61&language=en-US&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page

    );
}
