package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {

    EditText movieTitle, movieYear, movieDirector, movieActors, movieRating, movieReview;
    Button saveButton;
    TextView addMovieText;
    MovieDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);
        initializeElements();
        setListenerOnSave();


    }

    private void initializeElements() {

        movieTitle = findViewById(R.id.editText_movie_title);
        movieYear = findViewById(R.id.editText_movie_year);
        movieDirector = findViewById(R.id.editText_movie_director);
        movieActors = findViewById(R.id.editText_movie_actors);
        movieRating = findViewById(R.id.editText_movie_rating);
        movieReview = findViewById(R.id.editText_movie_review);
        saveButton = findViewById(R.id.button_save);
        addMovieText = findViewById(R.id.textView_addMovie);

        movieTitle.setBackgroundColor(Color.WHITE);
        movieDirector.setBackgroundColor(Color.WHITE);
        movieActors.setBackgroundColor(Color.WHITE);
        movieRating.setBackgroundColor(Color.WHITE);
        movieReview.setBackgroundColor(Color.WHITE);
        movieYear.setBackgroundColor(Color.WHITE);

        DB = new MovieDatabase(this);

    }

    public void setListenerOnSave() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = Integer.parseInt(movieYear.getText().toString());
                int rating = Integer.parseInt(movieRating.getText().toString());

                if (year < 1895) {
                    Toast.makeText(RegisterMovie.this, "Year must be over 1895", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rating > 10.0 || rating < 1.0) {
                    Toast.makeText(RegisterMovie.this, "Rating should be 1 to 10", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    DB.insertData(movieTitle.getText().toString(), movieYear.getText().toString(), movieDirector.getText().toString(), movieActors.getText().toString(), movieRating.getText().toString(), movieReview.getText().toString());

                }
            }
        });

    }

}