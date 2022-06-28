package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    MovieDatabase DB;
    ListView displayListView;
    Button lookUpButton;
    EditText searchTextBox;
    ArrayAdapter<String> adapter;
    public static ArrayList<String> searchList;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initializeElements();
        addItemsToList();
        setUpAdapterAndDisplay();
        searchListener();

    }

    private void initializeElements() {

        displayListView = findViewById(R.id.listView_display_search);
        lookUpButton = findViewById(R.id.button_lookup);
        searchList = new ArrayList<String>();
        DB = new MovieDatabase(this);
        searchTextBox = findViewById(R.id.editText_search_text);

    }

    private void addItemsToList() {


        Cursor data = DB.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(Search.this, "Database is empty!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String str = new String();

            // Add the info to the array list
            while (data.moveToNext()) {
                str = (data.getString(1) + "\n".concat((data.getString(3) + "\n")).concat((data.getString(4) + "\n")));
                searchList.add(str);


            }
        }
    }

    private void setUpAdapterAndDisplay() {

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchList);
        displayListView.setAdapter(adapter);


    }

    private void searchListener() {


        lookUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = searchTextBox.getText().toString();
                System.out.println(text);

                for (String item : searchList) {
                    if (item.contains(text)) {
                        System.out.println("have");
                        adapter.getFilter().filter(item);

                    }
                }

                /*for (int i = 0 ; i < searchList.size() ; i++) {

                    if (searchList.contains(text)){
                        adapter.getFilter().filter(text);
                        System.out.println("have");

                    }

                    if (searchList.get(i).matches(".*" +text+ ".*")) { //checks word by word
                        adapter.getFilter().filter(text);
                    }
                }
                System.out.println(searchList.size());*/


            }
        });

    }


}