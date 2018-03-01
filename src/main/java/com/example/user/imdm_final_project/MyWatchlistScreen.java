package com.example.user.imdm_final_project;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

//MyWatchlistScreen to show stored MyWatchlist which is extending AppCompatActivity
public class MyWatchlistScreen extends AppCompatActivity
{

    //creating instance of DataBaseHelper class
    DataBaseHelper dataBaseHelper;

    //creating arraylist to show the list of stored MyWatchlist
    ArrayList<StoringDataConstants> arrayList;

    //creating recyclerView object to show each list item
    RecyclerView recyclerView;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_watchlist_screen);

        //Setting recyclerView with its ID.
        recyclerView = findViewById(R.id.watch_recycler_view);

        //Creating object of LinearLayoutManager and setting to recyclerView.
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        //setting layout manager to recyclerview
        recyclerView.setLayoutManager(linearLayoutManager);

        //initialising arraylist
        arrayList = new ArrayList<>();

        //initialising databasehelper object
        dataBaseHelper = new DataBaseHelper(MyWatchlistScreen.this);

        //checking if MyWatchlist is not empty
        if(dataBaseHelper.rowWatchcount()!=0)
        {
            //getting the list of MyWatchlist movies stored in the local database
            arrayList = dataBaseHelper.getAllWatchMovie();
        }
        else
        {
            //if arraylist is empty then default list
            arrayList = new ArrayList<>();

            //displaying toast message
            Toast.makeText(MyWatchlistScreen.this,"List is empty",Toast.LENGTH_SHORT).show();
        }

        //favListAdapter object to show initialList into recyclerview
        FavListAdapter watchlistAdapter = new FavListAdapter(arrayList,MyWatchlistScreen.this);

        //if data is getting changed of recyclerView
        watchlistAdapter.notifyDataSetChanged();

        //setting adapter to recyclerView
        recyclerView.setAdapter(watchlistAdapter);

    }//end of onCreate method

}//end of class
