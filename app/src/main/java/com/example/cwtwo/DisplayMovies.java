package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class DisplayMovies extends AppCompatActivity {

    MovieDatabase DB;
    ListView displayListView;
    Button addToFavouritesButton;
    ArrayAdapter<String> adapter;
    public static ArrayList<String> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        initializeElements();
        displayMovieTitles();
        setOnClickListenerAddToFavourites();

    }

    private void setOnClickListenerAddToFavourites() {

        addToFavouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> selectedMovies = new ArrayList<String>();
                Cursor data = DB.getListContents();
                for (int i = 0; i < movieList.size(); i++) {

                    if (displayListView.isItemChecked(i)) {
                        selectedMovies.add(movieList.get(i));
                    }
                }
                DB.updateData(selectedMovies);
                Toast.makeText(DisplayMovies.this, "Added to Favourites!", Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void initializeElements() {

        displayListView = findViewById(R.id.listView_display_titles);
        addToFavouritesButton = findViewById(R.id.button_add_to_favourites);
        movieList = new ArrayList<String>();
        DB = new MovieDatabase(this);

    }

    //https://www.youtube.com/watch?v=SK98ayjhk1E - Saving data with SQLite and adding it to a ListView


    private void displayMovieTitles() {

        Cursor data = DB.getListContents();

        if (data.getCount() == 0){
            Toast.makeText(DisplayMovies.this, "Database is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            // Add the titles to the array list
            while(data.moveToNext()){
                movieList.add(data.getString(1));

                // Sort the titles in alphabetical order
                Collections.sort(movieList, String.CASE_INSENSITIVE_ORDER); //https://stackoverflow.com/questions/5815423/sorting-arraylist-in-alphabetical-order-case-insensitive
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, movieList);
                displayListView.setAdapter(adapter);
                //System.out.println(movieList);

            }

        }

    }






}