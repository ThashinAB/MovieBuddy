package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MovieDetails extends AppCompatActivity {

    EditText movieDetailTitle, movieDetailYear, movieDetailDirector, movieDetailActors, movieDetailRating, movieDetailReview;
    Button updateButton;
    MovieDatabase DB;
    RatingBar ratingBar;
    public static String titleHolder, yearHolder, directorHolder, castHolder, ratingHolder, reviewHolder;
    public static int idHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initializeElements();
        setDetailsToEditText();
        updateMovies();
        idHolder = EditMovies.itemPosition;
    }

    private void initializeElements() {

        movieDetailTitle = findViewById(R.id.editText_movie_details_title);
        movieDetailYear = findViewById(R.id.editText_movie_details_year);
        movieDetailDirector = findViewById(R.id.editText_movie_details_director);
        movieDetailActors = findViewById(R.id.editText_movie_details_actors);
        movieDetailRating = findViewById(R.id.editText_movie_details_rating);
        movieDetailReview = findViewById(R.id.editText_movie_details_review);
        ratingBar = findViewById(R.id.ratingBar);

        updateButton = (Button) findViewById(R.id.button_update);

    }

    private void setDetailsToEditText() {

        Intent intent = getIntent();

        if (intent != null) {

            titleHolder = intent.getStringExtra("title");
            movieDetailTitle.setText(titleHolder);

            yearHolder = intent.getStringExtra("year");
            movieDetailYear.setText(yearHolder);

            directorHolder = intent.getStringExtra("director");
            movieDetailDirector.setText(directorHolder);

            castHolder = intent.getStringExtra("actors");
            movieDetailActors.setText(castHolder);

            ratingHolder = intent.getStringExtra("rating");
            movieDetailRating.setText(ratingHolder);

            reviewHolder = intent.getStringExtra("review");
            movieDetailReview.setText(reviewHolder);

            //ratingBar.setRating(6.0f);
            String starNumber = movieDetailRating.getText().toString();
            ratingBar.setRating(Float.parseFloat(starNumber));

        }


    }

    private void updateMovies() {

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println(idHolder + "   " + titleHolder + "   " + yearHolder + "   " + directorHolder + "   " + castHolder + "   " + reviewHolder);


                if (titleHolder != null && !titleHolder.isEmpty() && yearHolder != null && directorHolder != null && castHolder != null && reviewHolder != null) {
                    DB.updateDataInEditMovies(EditMovies.itemPosition, titleHolder, yearHolder, directorHolder, castHolder, reviewHolder);
                } else {
                    //System.out.println("not there");
                }

            }
        });

    }


}