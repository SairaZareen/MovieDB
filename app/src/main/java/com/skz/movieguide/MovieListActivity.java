package com.skz.movieguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skz.movieguide.adapters.MovieAdapter;
import com.skz.movieguide.adapters.OnMovieClickListener;
import com.skz.movieguide.databinding.ActivityMainBinding;
import com.skz.movieguide.models.MovieModel;
import com.skz.movieguide.request.Service;
import com.skz.movieguide.response.MovieSearchResponse;
import com.skz.movieguide.utils.Credentials;
import com.skz.movieguide.utils.MovieApi;
import com.skz.movieguide.viewModels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieClickListener{

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    ActivityMainBinding binding;

    //ViewModel
    private MovieListViewModel movieListViewModel;

    boolean isPopular = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);
       // btn = findViewById(R.id.button);

       //recyclerView = findViewById(R.id.recycler_view);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(binding.toolbar);
        
        setUpSearchView();

        configureRecyclerView();
        observeData();
        observerPopularMovies();
        //Display popular movies
        movieListViewModel.searchMoviePopular(1);

        //searchMovieApi("fast",1);
        Log.v("Tagy", "ispop: " +isPopular);

    }

    private void observerPopularMovies() {

        movieListViewModel.getMoviesPopular().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing data changes
                if (movieModels !=null){
                    for (MovieModel movieModel: movieModels){
                        //Get data in log
                        Log.v("TagPopular","OnChanged : "+movieModel.getTitle());
                        movieAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }


    //Observing any change
    private void observeData()
    {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing data changes
                if (movieModels !=null){
                    for (MovieModel movieModel: movieModels){
                        //Get data in log
                        Log.v("Tag","OnChanged : "+movieModel.getTitle());
                        movieAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }

    //4-call search method in main activity
    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }

    //Initialize recyclerView
    private void  configureRecyclerView(){
        movieAdapter= new MovieAdapter(this);

        //recyclerView.setAdapter(movieAdapter);
        binding.recyclerView.setAdapter(movieAdapter);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       // recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //Pagination
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    //Api call for next page
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }
    private void GetRetrofitResponse() {

        MovieApi movieApi = Service.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY,"Jack Reacher",1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200){
                  //  Log.v("Tag","The response "+response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for (MovieModel movie: movies){
                        Log.v("Tag", "The title: "+movie.getTitle());
                    }
                }
                else {
                    try {
                        Log.v("Tag", "Error"+response.errorBody().string());
                        Toast.makeText(getApplicationContext(),"No Network! Please check your Internet Connection",Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    private void GetRetrofitResponseFromId(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(550,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200)
                {
                    MovieModel movie = response.body();
                    Log.v("Tag","The Response "+movie.getTitle());
                }
                else {
                    try {
                        Log.v("Tag","Error "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }


    @Override
    public void onMovieClick(int position) {
       Intent intent = new Intent(this,MovieDetailsActivity.class);
       intent.putExtra("movie",movieAdapter.getSelectedMovie(position));
        startActivity(intent);
       // Toast.makeText(this, "The Position "  +position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String category) {

    }

    private void setUpSearchView() {
       // final SearchView searchView = findViewById(R.id.search_view);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular = false;
            }
        });
        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //do what you want  searchview is not expanded
                return false;
            }
        });
    }

}