package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ratings extends AppCompatActivity {

    ListView displayListView;
    Button findMovieButton;
    ArrayAdapter<String> adapter;
    MovieDatabase DB;
    public static ArrayList<String> movieList;
    private int itemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        initializeElements();
        setUpAdapterAndDisplay();
        displayAllMovieTitles();
        setListenerOnListView();

    }

    private void initializeElements() {

        displayListView = findViewById(R.id.listView_rating);
        findMovieButton = findViewById(R.id.button_find_movie);
        DB = new MovieDatabase(this);
        movieList = new ArrayList<String>();


    }

    private void setUpAdapterAndDisplay() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, movieList);
        displayListView.setAdapter(adapter);


    }

    private void displayAllMovieTitles() {

        Cursor data = DB.retrieveMovies();

        if (data.getCount() == 0) {
            Toast.makeText(Ratings.this, "Database is empty!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Add the titles to the array list
            while (data.moveToNext()) {
                movieList.add(data.getString(1));


            }
        }

    }

    private void setListenerOnListView() {

        displayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemPosition = position;

            }
        });

        findMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = movieList.get(itemPosition).toString();

                // Here you have to getString using index
                System.out.println("id :" + (itemPosition + 1));
                //pos = position + 1;

                Intent intent = new Intent(Ratings.this, ImdbClass.class);

                intent.putExtra("title", title);

                //based on item add info to intent
                startActivity(intent);

            }
        });
    }


}