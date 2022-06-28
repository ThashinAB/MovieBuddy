package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button registerMovie;
    Button displayMovies;
    Button favourites;
    Button editMovies;
    Button search;
    Button ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeElements();
        addListenerOnButton();
    }

    private void addListenerOnButton() {

        registerMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterMovie.class);
                startActivity(intent);
            }
        });

        displayMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DisplayMovies.class);
                startActivity(intent);
            }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Favourites.class);
                startActivity(intent);
            }
        });

        editMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditMovies.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Search.class);
                startActivity(intent);
            }
        });

        ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Ratings.class);
                startActivity(intent);
            }
        });

    }

    private void initializeElements() {
        
        registerMovie = findViewById(R.id.button_register_movie);
        displayMovies = findViewById(R.id.button_display_movies);
        favourites = findViewById(R.id.button_favourites);
        editMovies = findViewById(R.id.button_edit_movies);
        search = findViewById(R.id.button_search);
        ratings = findViewById(R.id.button_ratings);
    }
}