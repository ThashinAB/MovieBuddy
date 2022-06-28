package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImdbClass extends AppCompatActivity {

    ListView imdbListView;
    ArrayList<String> movieList;
    ArrayList<String> movieIDList;
    ArrayList<String> moviePosterList;
    ArrayAdapter<String> adapter;
    String movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_class);

        initializeElements();
        //setUpAdapterAndDisplay();
        startThread();
        //setUpMovie();

        Intent intent = getIntent();
        if (intent != null) {
            movieName = intent.getStringExtra("title");
        }

    }

    private void initializeElements() {

        imdbListView = findViewById(R.id.listView_imdb);
        movieIDList = new ArrayList<>();
        movieList = new ArrayList<>();
        moviePosterList = new ArrayList<>();
    }


    //https://www.youtube.com/watch?v=scJy6gA230s
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {

            StringBuilder stringBuilder = new StringBuilder("");

            String stringURL = "https://imdb-api.com/en/API/SearchTitle/k_0au3u5o0/"+movieName; // k_0au3u5o0 from imdb webservice (json )
            try {
                URL url = new URL(stringURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }

                JSONObject jsonObj = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObj.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonMovie = jsonArray.getJSONObject(i);

                    String movieTitle = jsonMovie.getString("title");
                    String movieID = jsonMovie.getString("id");
                    String moviePoster = jsonMovie.getString("image");

                    movieList.add(movieTitle);
                    movieIDList.add(movieID);
                    moviePosterList.add(moviePoster);
                    System.out.println(movieID + " and " + movieTitle);
                    System.out.println(movieTitle);
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("exception");
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, movieList);
                    imdbListView.setAdapter(adapter);
                }
            });
        }
    });

    public void startThread() {
        thread.start();
    }

}

