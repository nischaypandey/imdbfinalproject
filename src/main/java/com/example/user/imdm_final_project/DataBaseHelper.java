package com.example.user.imdm_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;



//DataBaseHelper class which is extending SQLiteOpenHelper class to store data
public class DataBaseHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "database_name";

    //my favoriate list table
    private static final String DB_FAV_TABLE = "table_favoriate";

    //watchlist table
    private static final String DB_WATCH_TABLE = "table_watchlist";

    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_MOVIE_ID = "movie_id";
    private static final String KEY_RELEASE_DATE = "release_date";
    private static final String KEY_POPULARITY = "popularity";
    private static final String KEY_VOTECOUNT = "vote_count";
    private static final String KEY_VOTEAVERAGE = "vote_average";
    private static final String KEY_NAME = "movie_name";
    private static final String KEY_IMAGE = "image_data";

    //Table create statement for storing favoriate movie list
    String createFavTable="CREATE TABLE " + DB_FAV_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MOVIE_ID + " TEXT," + KEY_POPULARITY + " TEXT," + KEY_VOTECOUNT + " TEXT," + KEY_NAME + " TEXT," + KEY_VOTEAVERAGE + " TEXT," +KEY_RELEASE_DATE +  " TEXT," + KEY_IMAGE + " BLOB)";

    // Table create statement for storing watchlist movie list
    String createWatchTable="CREATE TABLE " + DB_WATCH_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MOVIE_ID + " TEXT," + KEY_POPULARITY + " TEXT," + KEY_VOTECOUNT + " TEXT," + KEY_NAME + " TEXT," + KEY_VOTEAVERAGE + " TEXT," +KEY_RELEASE_DATE +  " TEXT," + KEY_IMAGE + " BLOB)";


    //constructor
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table for storing favoriate movie list
        db.execSQL(createFavTable);

        // creating table for storing watchlist movie list
        db.execSQL(createWatchTable);
    }

    //onpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_FAV_TABLE);

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_WATCH_TABLE);

        // create new table
        onCreate(db);
    }

    //method to return number of rows in favoriate movielist table
    public int rowFavcount()
    {
        int rowFavcount=0;

        //open connection to read database
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(false,DB_FAV_TABLE,null,null,null,null,null,null,null);

        //moving cursor to first row of database
        cursor.moveToFirst();

        //counting number of rows and storing in row variable
        rowFavcount=cursor.getCount();

        //returning number of rows
        return rowFavcount;
    }

    //method to return number of rows in watchlist movielist table
    public int rowWatchcount()
    {
        int rowWatchcount=0;

        //open connection to read database
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(false,DB_WATCH_TABLE,null,null,null,null,null,null,null);

        //moving cursor to first row of database
        cursor.moveToFirst();

        //counting number of rows and storing in row variable
        rowWatchcount=cursor.getCount();

        //returning number of rows
        return rowWatchcount;
    }

    //method to add movie in favoriate movielist
    public void addFavEntry( StoringDataConstants storingData) throws SQLiteException
    {
        //creating instance of SQLiteDatabase class
        SQLiteDatabase database = this.getWritableDatabase();

        //creating content values object to store details
        ContentValues cv = new  ContentValues();

        //adding deatils into content values object
        cv.put(KEY_ID,storingData.getStoreid());
        cv.put(KEY_MOVIE_ID,storingData.getMovieId());
        cv.put(KEY_RELEASE_DATE,storingData.getReleasedate());
        cv.put(KEY_POPULARITY,storingData.getPopularity());
        cv.put(KEY_VOTECOUNT,storingData.getVotecount());
        cv.put(KEY_VOTEAVERAGE,storingData.getVoteAverage());
        cv.put(KEY_NAME,storingData.getStorename());


        //Converting Bitmap image to byte array.
        Bitmap bitmap=storingData.getStoredimage();
        byte[] img;

        if(bitmap!=null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            img = byteArrayOutputStream.toByteArray();
        }
        else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            img = byteArrayOutputStream.toByteArray();
        }

        //inserting image into content values
        cv.put(KEY_IMAGE,img);

        //inserting movie details into table thorugh contentvalues object
        database.insert( DB_FAV_TABLE, null, cv );

    }

    //method to add movie in watchlist movielist
    public void addWatchEntry( StoringDataConstants storingData) throws SQLiteException
    {
        //creating instance of SQLiteDatabase class
        SQLiteDatabase database = this.getWritableDatabase();

        //creating content values object to store details
        ContentValues cv = new  ContentValues();

        //adding deatils into content values object
        cv.put(KEY_ID,storingData.getStoreid());
        cv.put(KEY_MOVIE_ID,storingData.getMovieId());
        cv.put(KEY_RELEASE_DATE,storingData.getReleasedate());
        cv.put(KEY_POPULARITY,storingData.getPopularity());
        cv.put(KEY_VOTECOUNT,storingData.getVotecount());
        cv.put(KEY_VOTEAVERAGE,storingData.getVoteAverage());
        cv.put(KEY_NAME,storingData.getStorename());

        //Converting Bitmap image to byte array.
        Bitmap bitmap=storingData.getStoredimage();
        byte[] img;

        if(bitmap!=null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            img = byteArrayOutputStream.toByteArray();
        }
        else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            img = byteArrayOutputStream.toByteArray();
        }

        //inserting image into content values
        cv.put(KEY_IMAGE,img);

        //inserting movie details into table thorugh contentvalues object
        database.insert( DB_WATCH_TABLE, null, cv );
    }

    //method to get the all favmovielist
    public ArrayList<StoringDataConstants> getAllFavMovie()
    {
        //creating arraylist to get the stored favmovielist
        ArrayList<StoringDataConstants> list = new ArrayList<>();

        //creating instance of SQLiteDatabase class
        SQLiteDatabase database = this.getReadableDatabase();

        //query to fetch the favmovielist from table
        String query = "select * from "+DB_FAV_TABLE;

        //Creating the cursor by using the rawQuery method of db.
        Cursor cursor = database.rawQuery(query,null);

        //creating storingData object to get the details in Storing data form
        StoringDataConstants storingData = null;

        //checking if table is not empty
        if (cursor!=null)
        {
            //moving cursor to the first row of table
            cursor.moveToFirst();

            do{

                //initialising object storingData
                storingData = new StoringDataConstants();

                //storing details in storingData object from databse
                storingData.setStoreid(cursor.getString(0));
                storingData.setMovieId(cursor.getString(1));
                storingData.setPopularity(cursor.getString(2));
                storingData.setVotecount(cursor.getString(3));
                storingData.setStorename(cursor.getString(4));
                storingData.setVoteAverage(cursor.getString(5));
                storingData.setReleasedate(cursor.getString(6));

                //Converting byte array into Bitmap Image.
                byte[] img=cursor.getBlob(7);
                storingData.setStoredimage(BitmapFactory.decodeByteArray(img,0,img.length));

                //adding storingData object into arraylist of favmovielist
                list.add(storingData);

            }while (cursor.moveToNext());
        }

        //closing cursor
        cursor.close();

        //returning arraylist of favmovielist
        return list;

    }//end of method

    //method to get the all watchlistmovielist
    public ArrayList<StoringDataConstants> getAllWatchMovie()
    {
        //creating arraylist to get the stored watchlistmovielist
        ArrayList<StoringDataConstants> list = new ArrayList<>();

        //creating instance of SQLiteDatabase class
        SQLiteDatabase database = this.getReadableDatabase();

        //query to fetch the watchlistmovielist from table
        String query = "select * from "+DB_WATCH_TABLE;

        //Creating the cursor by using the rawQuery method of db.
        Cursor cursor = database.rawQuery(query,null);

        //creating storingData object to get the details in Storing data form
        StoringDataConstants storingData = null;

        //checking if table is not empty
        if (cursor!=null)
        {
            //moving cursor to the first row of table
            cursor.moveToFirst();

            do{
                //initialising object storingData
                storingData = new StoringDataConstants();

                //storing details in storingData object from databse
                storingData.setStoreid(cursor.getString(0));
                storingData.setMovieId(cursor.getString(1));
                storingData.setPopularity(cursor.getString(2));
                storingData.setVotecount(cursor.getString(3));
                storingData.setStorename(cursor.getString(4));
                storingData.setVoteAverage(cursor.getString(5));
                storingData.setReleasedate(cursor.getString(6));

                //Converting byte array into Bitmap Image.
                byte[] img=cursor.getBlob(7);
                storingData.setStoredimage(BitmapFactory.decodeByteArray(img,0,img.length));

                //adding storingData object into arraylist of watchlistmovielist
                list.add(storingData);

            }while (cursor.moveToNext());
        }

        //closing cursor
        cursor.close();

        //returning arraylist of watchlistmovielist
        return list;

    }//end of method

}//end of class