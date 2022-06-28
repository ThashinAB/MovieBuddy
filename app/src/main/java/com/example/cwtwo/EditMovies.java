package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovies extends AppCompatActivity {

    ListView allMoviesListView;
    MovieDatabase DB;
    ArrayAdapter<String> adapter;
    ArrayList<String> titleList;
    ArrayList<String> yearList;
    ArrayList<String> directorList;
    ArrayList<String> actorsList;
    ArrayList<String> ratingList;
    ArrayList<String> reviewList;
    public static int itemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        initializeElements();
        displayAllMovieTitles();
        setListenerOnListView();

    }


    private void setListenerOnListView() {

        allMoviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = titleList.get(position).toString();
                String year = yearList.get(position).toString();
                String director = directorList.get(position).toString();
                String actors = actorsList.get(position).toString();
                String rating = ratingList.get(position).toString();
                String review = reviewList.get(position).toString();

                // Here you have to getString using index
                System.out.println("id :" + (position + 1));
                itemPosition = position + 1;

                Intent intent = new Intent(EditMovies.this, MovieDetails.class);

                intent.putExtra("title", title);
                intent.putExtra("year", year);
                intent.putExtra("director", director);
                intent.putExtra("actors", actors);
                intent.putExtra("rating", rating);
                intent.putExtra("review", review);
                //based on item add info to intent

                startActivity(intent);
            }
        });
    }

    private void displayAllMovieTitles() {


        Cursor data = DB.retrieveMovies();

        if (data.getCount() == 0) {
            Toast.makeText(EditMovies.this, "Database is empty!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Add the titles to the array list
            while (data.moveToNext()) {
                titleList.add(data.getString(1));
                yearList.add(data.getString(2));
                directorList.add(data.getString(3));
                actorsList.add(data.getString(4));
                ratingList.add(data.getString(5));
                reviewList.add(data.getString(6));

                // Sort the titles in alphabetical order
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titleList);
                allMoviesListView.setAdapter(adapter);

                //System.out.println(movieList);

            }

        }

    }

    private void initializeElements() {

        allMoviesListView = findViewById(R.id.listView_display_all_movies);
        titleList = new ArrayList<String>();
        yearList = new ArrayList<String>();
        directorList = new ArrayList<String>();
        actorsList = new ArrayList<String>();
        ratingList = new ArrayList<String>();
        reviewList = new ArrayList<String>();
        DB = new MovieDatabase(this);

    }


}