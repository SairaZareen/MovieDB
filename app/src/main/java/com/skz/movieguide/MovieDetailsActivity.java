package com.skz.movieguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skz.movieguide.models.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {

private ImageView imageMovieDetails;
private TextView titleDetails, descDetails;
private RatingBar ratingBarDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageMovieDetails = findViewById(R.id.imageView_details);
        titleDetails      = findViewById(R.id.textView_title_details);
        descDetails       = findViewById(R.id.textView_desc_details);
        ratingBarDetails  = findViewById(R.id.ratingBar_details);
        
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            titleDetails.setText(movieModel.getTitle());
            descDetails.setText(movieModel.getMovie_overview());
          //  descDetails.setText("MovieOverview\nMovieOverview\nMoviewOverview");
            ratingBarDetails.setRating((movieModel.getVote_average())/2);

         //   Log.v("Tags" ,"X"+ movieModel.getMovie_overview());
         //   Log.v("Tags" ,"X"+ movieModel.getTitle());


            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            +movieModel.getPoster_path())
                    .into(imageMovieDetails);


        }

    }
}