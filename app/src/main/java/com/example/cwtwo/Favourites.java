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

public class Favourites extends AppCompatActivity {

    MovieDatabase DB;
    ListView displayListView;
    Button saveButton;
    ArrayList<String> selectedMovieList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        initializeElements();
        displaySelectedMovies();
        setListenerOnSaveButton();
        setCheckBoxesTrue();



    }

    private void setListenerOnSaveButton() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // To Replace all the values in the Favourites column to null
                DB.resetColumn();

                ArrayList<String> selectedMovies = new ArrayList<String>();
                for (int i = 0; i < selectedMovieList.size(); i++) {

                    if (displayListView.isItemChecked(i)) {
                        selectedMovies.add(selectedMovieList.get(i));
                    }
                }
                DB.updateData(selectedMovies);
                Toast.makeText(Favourites.this, "Saved", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void displaySelectedMovies() {

        Cursor data = DB.getListContents();

        if (data.getCount() == 0){
            Toast.makeText(Favourites.this, "Database is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            // Add the titles to the array list
            while(data.moveToNext()){

                if (!data.isNull(7) && !data.getString(7).equals("")) {

                    selectedMovieList.add(data.getString(7));
                }

                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, selectedMovieList);
                displayListView.setAdapter(adapter);

            }

        }

    }

    private void setCheckBoxesTrue(){

        for (int i = 0; i < selectedMovieList.size(); i++) {
            displayListView.setItemChecked(i,true);
        }
    }

    private void initializeElements() {

        displayListView = findViewById(R.id.listView_display_selected_movies);
        saveButton = findViewById(R.id.button_save);
        DB = new MovieDatabase(this);
        selectedMovieList = new ArrayList<String>();


    }


}