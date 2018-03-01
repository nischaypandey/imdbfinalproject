package com.example.user.imdm_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import java.util.ArrayList;

//MyFavoriateScreen to show stored MyfavoriateMovieList which is extending AppCompatActivity
public class MyFavoriateScreen extends AppCompatActivity
{

    //creating instance of DataBaseHelper class
    DataBaseHelper dataBaseHelper;

    //creating arraylist to show the list of stored MyfavoriateMovieList
    ArrayList<StoringDataConstants> arrayList;

    //creating recyclerView object to show each list item
    RecyclerView recyclerView;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favoriate_screen);

        //Setting recyclerView with its ID.
        recyclerView = findViewById(R.id.fav_recycler_view);

        //Creating object of LinearLayoutManager and setting to recyclerView.
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        //setting layout manager to recyclerview
        recyclerView.setLayoutManager(linearLayoutManager);

        //initialising arraylist
        arrayList = new ArrayList<>();

        //initialising databasehelper object
        dataBaseHelper = new DataBaseHelper(MyFavoriateScreen.this);

        //checking if favoriateList is not empty
        if(dataBaseHelper.rowFavcount()!=0)
        {
            //getting the list of favoriateList movies stored in the local database
            arrayList = dataBaseHelper.getAllFavMovie();
        }
        else
        {
            //if arraylist is empty then default list
            arrayList = new ArrayList<>();

            //displaying toast message
            Toast.makeText(MyFavoriateScreen.this,"List is empty",Toast.LENGTH_SHORT).show();
        }

        //favListAdapter object to show initialList into recyclerview
        FavListAdapter favListAdapter = new FavListAdapter(arrayList,MyFavoriateScreen.this);

        //if data is getting changed of recyclerView
        favListAdapter.notifyDataSetChanged();

        //setting adapter to recyclerView
        recyclerView.setAdapter(favListAdapter);

    }//end of onCreate method

}//end of class
