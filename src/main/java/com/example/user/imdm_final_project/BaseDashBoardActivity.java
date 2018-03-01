package com.example.user.imdm_final_project;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.ArrayList;

public class BaseDashBoardActivity extends AppCompatActivity {

    //creating instance of toolbar
    Toolbar toolbar;

    //creating instance of recyclerview
    public RecyclerView recyclerView;

    //creating instance of option showing images
    public ImageView option1, option2;

    //creating arraylist to store showing movie list
    public ArrayList<Constants> initialList = new ArrayList<>();

    //creating instance of rvAdapter class to show list of movies of initialst
    public RVAdapter rvAdapter;

    //creating movieurl object to pass movie url
    public String movieurl = null;

    //strings of different movie url's
    public String mostPopularMovieUrl = "http://api.themoviedb.org/3/movie/popular?api_key=8496be0b2149805afa458ab8ec27560c";
    public String upcomingMovieUrl = "http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c";
    public String latestMovieUrl = "http://api.themoviedb.org/3/movie/latest?api_key=8496be0b2149805afa458ab8ec27560c";
    public String nowPlayingMovieUrl = "http://api.themoviedb.org/3/movie/now_playing?api_key=8496be0b2149805afa458ab8ec27560c";
    public String topRatedMovieUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_dash_board);

        //setting toolbar with its ID
        toolbar = findViewById(R.id.toolbar);

        //setting toolbar to action bar
        setSupportActionBar(toolbar);

        //setting options to it's ID
        option1 = toolbar.findViewById(R.id.option1);
        option2 = toolbar.findViewById(R.id.option2);

        //Setting recyclerView with its ID.
        recyclerView = findViewById(R.id.recycler_view);

        //Setting RecyclerView adapter.
        rvAdapter = new RVAdapter(initialList, BaseDashBoardActivity.this);

        //refreshing the arrayList
        rvAdapter.notifyDataSetChanged();

        //settings adapter to recyclerview
        recyclerView.setAdapter(rvAdapter);


        //onClick of listofmovies image
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(BaseDashBoardActivity.this, option1);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu1, popup.getMenu());

                //onClick of popup menu option selected
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //getting item id
                        int id = menuItem.getItemId();

                        //switch case for each item selected
                        switch (id) {
                            case R.id.most_popular:

                                //checking if device is connected to internet or not
                                if (isIntenetConnected()) {
                                    movieurl = mostPopularMovieUrl;

                                    //creating object of MovieList class and passing mostpopularmovieurl into it's constructor
                                    MovieList movieList = new MovieList(BaseDashBoardActivity.this, movieurl);
                                } else {
                                    //displaying toast message
                                    Toast.makeText(BaseDashBoardActivity.this, "No Intenet connection found", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.upcoming_movies:

                                //checking if device is connected to internet or not
                                if (isIntenetConnected()) {
                                    movieurl = upcomingMovieUrl;

                                    //creating object of MovieList class and passing upcomingMovieUrl into it's constructor
                                    MovieList movieList = new MovieList(BaseDashBoardActivity.this, movieurl);
                                } else {
                                    //displaying toast message
                                    Toast.makeText(BaseDashBoardActivity.this, "No Intenet connection found", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.latest_movies:

                                //checking if device is connected to internet or not
                                if (isIntenetConnected()) {
                                    movieurl = latestMovieUrl;

                                    //creating object of LatestMovies class and passing latestMovieUrl into it's constructor
                                    LatestMovies latestMovies = new LatestMovies(BaseDashBoardActivity.this, movieurl);
                                } else {
                                    //displaying toast message
                                    Toast.makeText(BaseDashBoardActivity.this, "No Intenet connection found", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.now_playing:

                                //checking if device is connected to internet or not
                                if (isIntenetConnected()) {
                                    movieurl = nowPlayingMovieUrl;

                                    //creating object of MovieList class and passing nowPlayingMovieUrl into it's constructor
                                    MovieList movieList = new MovieList(BaseDashBoardActivity.this, movieurl);
                                } else {
                                    //displaying toast message
                                    Toast.makeText(BaseDashBoardActivity.this, "No Intenet connection found", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case R.id.top_rated:

                                //checking if device is connected to internet or not
                                if (isIntenetConnected()) {
                                    movieurl = topRatedMovieUrl;

                                    //creating object of MovieList class and passing topRatedMovieUrl into it's constructor
                                    MovieList movieList = new MovieList(BaseDashBoardActivity.this, movieurl);
                                } else {
                                    //displaying toast message
                                    Toast.makeText(BaseDashBoardActivity.this, "No Intenet connection found", Toast.LENGTH_SHORT).show();
                                }
                                break;

                        }
                        Toast.makeText(BaseDashBoardActivity.this, "You clicked : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                //showing popup menu
                popup.show();
            }
        });//end of method

        //onClick of second popupmenu image
        option2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(BaseDashBoardActivity.this, option2);

                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu2, popup.getMenu());

                //onClick of popup menu option selected
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        //getting item id
                        int id =menuItem.getItemId();

                        //switch statement for handling each case
                        switch (id)
                        {
                            case R.id.my_favoriates:

                                //calling MyFavoriateScreen Activity on click of my_favoriate option
                                Intent intent = new Intent(BaseDashBoardActivity.this,MyFavoriateScreen.class);
                                startActivity(intent);

                                break;

                            case R.id.my_watchlist:

                                //calling MyWatchListScreen Activity on click of my_watchlist option
                               Intent intent1 = new Intent(BaseDashBoardActivity.this,MyWatchlistScreen.class);
                               startActivity(intent1);

                                break;

                            case R.id.refresh:

                                //displaying toast on click of refresh
                                Toast.makeText(BaseDashBoardActivity.this,"Page Refreshed",Toast.LENGTH_SHORT).show();
                        }

                        //displaying toast message on click of any item
                        Toast.makeText(BaseDashBoardActivity.this,"You clicked : "+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                //showing popup menu
                popup.show();
            }
        });//end of method onclick

    }//end of oncreate method



    private boolean isIntenetConnected() {

        //initialising status of the device whether is connected to internet or not
        boolean isConnected = false;

        //creating connectivityManager object to check connection status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        //getting network information
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //if connected to internet then changing its value
        if (networkInfo != null) {
            isConnected = true;
        }

        //returning connection status
        return isConnected;
    }
    public class LatestMovies implements HandleJsonListener,ItemClicked
    {

        //constructor
        public LatestMovies(Context context, String URL)
        {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(BaseDashBoardActivity.this,movieurl,this);

            //executing the interface
            networkRequestTask.execute();
        }

        //updateData method
        @Override
        public void updateData(String json)
        {
            //initialising arraylist
            initialList = new ArrayList<>();

            float popularity=0;

            try {

                //creating parent Json object
                JSONObject parentobject = new JSONObject(json);

                //creating object of Constants class to add in arraylist
                Constants constants = new Constants();

                //storing respective fileds of json array in constants object
                constants.setId(parentobject.getString("id"));
                constants.setImageUrl(parentobject.getString("poster_path"));
                constants.setName(parentobject.getString("title"));
                constants.setReleaseDate(parentobject.getString("release_date"));
                popularity = Float.parseFloat(parentobject.getString("popularity"))/100;
                if(popularity>5)
                {
                    popularity = 5;
                }
                constants.setPolpularity(popularity);
                constants.setVotesAverage(parentobject.getString("vote_average"));
                constants.setVotesCount(parentobject.getString("vote_count"));

                //adding object of constants type into arraylist
                initialList.add(constants);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Creating object of LinearLayoutManager and setting to recyclerView.
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

            //setting layout manager to recyclerview
            recyclerView.setLayoutManager(linearLayoutManager);

            //rvAdapter object to show initialList into recyclerview
            RVAdapter rvAdapter = new RVAdapter(initialList,BaseDashBoardActivity.this);

            //if data is getting changed of recyclerView
            rvAdapter.notifyDataSetChanged();

            //setting adapter to recyclerView
            recyclerView.setAdapter(rvAdapter);

            //on click of any item of recyclerView calling setClickListener method which is implemented below
            rvAdapter.setClickListener(this);

        }

        //on click of any item of recyclerView
        @Override
        public void onCLick(View view, int position)
        {
            //getting id of selected item of recyclerView
            Constants constants=initialList.get(position);

            //calling MovieDetailScreen Activity on click of any item which is showing it's detail
            Intent intent = new Intent(BaseDashBoardActivity.this,MovieDetailScreen.class);

            //passing Id of movie to MovieDetailScreen class
            intent.putExtra("id",constants.getId());

            //starting MovieDetailScreen activity
            startActivity(intent);

        }//end of method

    }//end of latestmovie class

    //MovieList class which is implementing HandleJsonListener & ItemClicked listener
    public class MovieList implements HandleJsonListener, ItemClicked            //*********************************
    {


        //constructor
        public MovieList(Context context, String URL) {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(BaseDashBoardActivity.this, movieurl, this);

            //executing the interface
            networkRequestTask.execute();
        }


        //updatedata method
        @Override
        public void updateData(String json) {
            //initialising arraylist
            initialList = new ArrayList<>();

            try {

                //creating parent Json object
                JSONObject parentObject = new JSONObject(json);

                //getting array of Json objects in name results
                JSONArray array = parentObject.getJSONArray("results");

                //loop for adding whole array into arraylist
                for (int i = 0; i < array.length(); i++) {
                    float popularity = 0;

                    //creating child Json object to access child array
                    JSONObject childObject = array.getJSONObject(i);

                    //creating object of Constants class to add in arraylist
                    Constants constants = new Constants();

                    //storing respective fields of json array in constants object
                    constants.setId(childObject.getString("id"));
                    constants.setImageUrl(childObject.getString("poster_path"));
                    constants.setName(childObject.getString("title"));
                    constants.setReleaseDate(childObject.getString("release_date"));
                    popularity = Float.parseFloat(childObject.getString("popularity")) / 100;
                    if (popularity > 5) {
                        popularity = 5;
                    }
                    constants.setPolpularity(popularity);
                    constants.setVotesAverage(childObject.getString("vote_average"));
                    constants.setVotesCount(childObject.getString("vote_count"));

                    //adding object of constants type into arraylist
                    initialList.add(constants);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Creating object of LinearLayoutManager and setting to recyclerView.
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

            //setting layout manager to recyclerview
            recyclerView.setLayoutManager(linearLayoutManager);

            //rvAdapter object to show initialList into recyclerview
            RVAdapter rvAdapter = new RVAdapter(initialList, BaseDashBoardActivity.this);

            //if data is getting changed of recyclerView
            rvAdapter.notifyDataSetChanged();

            //setting adapter to recyclerView
            recyclerView.setAdapter(rvAdapter);

            //on click of any item of recyclerView calling setClickListener method which is implemented below
            rvAdapter.setClickListener(this);
        }

        //on click of any item of recyclerView
        @Override
        public void onCLick(View view, int position) {
            //getting id of selected item of recyclerView
            Constants constants = initialList.get(position);

            //calling MovieDetailScreen Activity on click of any item which is showing it's detail
            Intent intent = new Intent(BaseDashBoardActivity.this, MovieDetailScreen.class);

            //passing Id of movie to MovieDetailScreen class
            intent.putExtra("id", constants.getId());

            //starting MovieDetailScreen activity
            startActivity(intent);

        }//end of method
    }
}
