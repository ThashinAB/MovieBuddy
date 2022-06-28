package com.example.cwtwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CWTwo.db";


    public static final String TABLE_NAME = "Movies";
    public static final int DATABASE_VER = 1;

    Context context;
    SQLiteDatabase db;

// https://www.youtube.com/playlist?list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07 DB tutorial

    public MovieDatabase(Context ctx){
        super(ctx, DATABASE_NAME,null,DATABASE_VER);
        context = ctx;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT,movie_title TEXT,movie_year INTEGER,movie_director TEXT,movie_actors TEXT,movie_rating REAL,movie_review TEXT,favourites TEXT);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);

        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE "+ TABLE_NAME +" ADD COLUMN movie_favourites String DEFAULT 0");
        }

    }

    public void insertData(String title, String year, String director, String actors, String rating, String review){
        db = getWritableDatabase();

        db.execSQL("insert into "+ TABLE_NAME +" (movie_title,movie_year,movie_director,movie_actors,movie_rating,movie_review) values('"+title+"','"+year+"','"+director+"','"+actors+"','"+rating+"','"+review+"');");
        Toast.makeText(context,"Data saved!",Toast.LENGTH_SHORT).show();
    }

    public boolean updateData(ArrayList allSelectedMovies) {

        String TB_ONE_COLUMN_ONE = "ID";
        String TB_ONE_COLUMN_TWO = "favourites";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < allSelectedMovies.size(); i++) {

            contentValues.put(TB_ONE_COLUMN_ONE, i + 1);
            contentValues.put(TB_ONE_COLUMN_TWO, allSelectedMovies.get(i).toString());
            //System.out.println(contentValues);
            db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { String.valueOf(i + 1) });
        }
        return true;
    }

    public boolean resetColumn() {

        Cursor data = getListContents();

        String TB_ONE_COLUMN_ONE = "ID";
        String TB_ONE_COLUMN_TWO = "favourites";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        db = this.getReadableDatabase();
        long numOfRows = DatabaseUtils.queryNumEntries(db, TABLE_NAME);// number of rows

        for (int i = 0; i < (numOfRows); i++) {

            contentValues.put(TB_ONE_COLUMN_ONE, i + 1);
            contentValues.put(TB_ONE_COLUMN_TWO, "");
            //System.out.println(contentValues);
            db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { String.valueOf(i + 1) });
        }
        return true;
    }

    public boolean setNull() {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("favourites");
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public void insertToFavourites(String favourites){
        db = getWritableDatabase();
        db.execSQL("insert into "+ TABLE_NAME +" (favourites) values('"+favourites+"');");
        //System.out.println(favourites);
    }

    public void getAll() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        StringBuilder str = new StringBuilder();

        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                do {
                    String title = cursor.getString(1);
                    String year = cursor.getString(2);
                    String director = cursor.getString(3);
                    String actors = cursor.getString(4);
                    String rating = cursor.getString(5);
                    String review = cursor.getString(6);
                    //String favourites = cursor.getString(7);
                    str.append(title + "        " + year + "       " + director + "      " + actors + "        " + rating + "       " + review + "\n");


                } while (cursor.moveToNext());
            }
        }

            Toast.makeText(context, str.toString(), Toast.LENGTH_SHORT).show();

    }

    public Cursor getListContents(){
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return data;
    }


    public Cursor retrieveMovies(){

        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        return cursor;
    }

    public boolean updateDataInEditMovies(int position ,String title,String year,String director,String actors,String review) {

       String TB_ONE_COLUMN_ONE = "ID";
        String TB_ONE_COLUMN_TWO = "movie_title";
        String TB_ONE_COLUMN_THREE = "movie_year";
        String TB_ONE_COLUMN_FOUR = "movie_director";
        String TB_ONE_COLUMN_FIVE = "movie_actors";
        String TB_ONE_COLUMN_SIX = "movie_rating";
        String TB_ONE_COLUMN_SEVEN = "movie_review";


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TB_ONE_COLUMN_ONE, position);
        contentValues.put(TB_ONE_COLUMN_TWO, title);
        contentValues.put(TB_ONE_COLUMN_THREE, year);
        contentValues.put(TB_ONE_COLUMN_FOUR, director);
        contentValues.put(TB_ONE_COLUMN_FIVE, actors);
        //contentValues.put(TB_ONE_COLUMN_SIX, rating);
        contentValues.put(TB_ONE_COLUMN_SEVEN, review);

        System.out.println(contentValues);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { String.valueOf(position)});
        System.out.println("yesss");

        return true;

    }

}
