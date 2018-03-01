package com.example.user.imdm_final_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//MovieDetailScreen class to show movie complete details which is extending AppCompatActivity class
public class MovieDetailScreen extends AppCompatActivity {

    //creating reference of layout objects
    Toolbar toolbar;
    ImageView moviePoster;
    TextView Title,Description,ReleaseDate,Budget,Revenue,Status,VotesCount,AverageVotes,Overview;
    RatingBar multipleRating,singleRating;
    ImageView watchList,favoriateList,optionbtn1,optionbtn2;
    RecyclerView poster,Toalcast,Totalcrew;
    String MovieDetailURL,PosterURL,CastAndCrewURL;

    //creating and initialising all the arraylists and instances
    ParticularMovieConstant particularMovieConstant = new ParticularMovieConstant();
    public ArrayList<Posters> mposters = new ArrayList<>();
    public ArrayList<CastMember> mCast = new ArrayList<>();
    public ArrayList<CrewMember> mCrew = new ArrayList<>();
    public DataBaseHelper dataBaseHelper1,dataBaseHelper2;
    public ArrayList<StoringDataConstants> myfavmovie ;
    public ArrayList<StoringDataConstants> mywatchlist;
    String id;

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        //setting toolbar with it's id
        toolbar = findViewById(R.id.toolbarMovieScreen);

        //setting toolbar to support in place of action bar
        setSupportActionBar(toolbar);

        //setting reference with their iD's
        optionbtn1 = findViewById(R.id.detailoption1);
        optionbtn2 = findViewById(R.id.detailoption2);
        moviePoster = findViewById(R.id.movie_poster);
        Title = findViewById(R.id.movie_title);
        Description = findViewById(R.id.tagline);
        ReleaseDate = findViewById(R.id.movie_release_date);
        Budget = findViewById(R.id.budget);
        Revenue = findViewById(R.id.revenue);
        Status = findViewById(R.id.status);
        VotesCount = findViewById(R.id.users);
        AverageVotes = findViewById(R.id.average_vote);
        Overview = findViewById(R.id.overview);
        multipleRating = findViewById(R.id.movie_rating);
        singleRating = findViewById(R.id.single_movie_rating);
        watchList = findViewById(R.id.my_watchlist);
        favoriateList = findViewById(R.id.my_favoriates);
        poster = findViewById(R.id.poster_view);
        Toalcast = findViewById(R.id.cast_details);
        Totalcrew = findViewById(R.id.crew_details);

        //getting intent from MainActivity
        Intent intent = getIntent();

        //getting movie id from MainActivity
        id = intent.getStringExtra("id");

        //strings to fetch the details from server
        MovieDetailURL = "http://api.themoviedb.org/3/movie/"+id+"?api_key=8496be0b2149805afa458ab8ec27560c";
        PosterURL = "http://api.themoviedb.org/3/movie/"+id+"/images?api_key=8496be0b2149805afa458ab8ec27560c";
        CastAndCrewURL = "http://api.themoviedb.org/3/movie/"+id+"/credits?api_key=8496be0b2149805afa458ab8ec27560c";

        //creating MovieDetails1 object to retrieve all the movie details
        final MovieDetails movieDetails1 = new MovieDetails();

        //creating PosterSet object to retrieve all the posters of the movie
        final PosterSet posterSet = new PosterSet();

        //creating CastAndCrew object to retrieve all the cast and crew deatils of the movie
        CastAndCrewSet castAndCrewSet = new CastAndCrewSet();

        //on click of watchlist button
        watchList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                int flag = 0;

                //creating instance of DataBaseHelper class
                dataBaseHelper1 = new DataBaseHelper(MovieDetailScreen.this);

                //checking if watchlist is not empty
                if (dataBaseHelper1.rowWatchcount() != 0) {

                    //getting the list of watchlist movies stored in the local database
                    mywatchlist = dataBaseHelper1.getAllWatchMovie();
                } else {

                    //if arraylist is empty then default list
                    mywatchlist = new ArrayList<>();
                }

                //checking in the whole list if the movie is already has been added in the watchlist or not
                for (int i = 0; i < mywatchlist.size(); i++)
                {
                    if (mywatchlist.get(i).getMovieId().equals(id))
                    {
                        //Toast message
                        Toast.makeText(MovieDetailScreen.this,"Movie already has been added",Toast.LENGTH_SHORT).show();

                        flag = 1;
                        break;
                    }
                }

                //checking if the condition of already not added is satisfied or not
                if (flag == 0)
                {

                    //storing details in watchlist table of database if already has not been added
                    try {

                        //getting url of image to store in the database
                        URL url = new URL("http://image.tmdb.org/t/p/w500"+particularMovieConstant.getPosterURL());

                        //create object of WatchlistAsync class to store the data in database
                        WatchlistAsync watchlistAsync = new WatchlistAsync();

                        //executing asynctask
                        watchlistAsync.execute(url);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //displaying toast message
                    Toast.makeText(MovieDetailScreen.this, "Movie added to your watching list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //on click of favoriateList button
        favoriateList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                int flag = 0;

                //creating instance of DataBaseHelper class
                dataBaseHelper2 = new DataBaseHelper(MovieDetailScreen.this);

                //checking if favoriateList is not empty
                if (dataBaseHelper2.rowFavcount() != 0) {

                    //getting the list of favoriateList movies stored in the local database
                    myfavmovie = dataBaseHelper2.getAllFavMovie();
                } else {

                    //if arraylist is empty then default list
                    myfavmovie = new ArrayList<>();
                }

                //checking in the whole list if the movie is already has been added in the favoriateList or not
                for (int i = 0; i < myfavmovie.size(); i++)
                {
                    if (myfavmovie.get(i).getMovieId().equals(id))
                    {

                        //displaying toast message
                        Toast.makeText(MovieDetailScreen.this,"Movie already has been added",Toast.LENGTH_SHORT).show();

                        flag = 1;
                        break;
                    }
                }

                //checking if the condition of already not added is satisfied or not
                if (flag == 0)
                {

                    //storing details in favoriateList table of database if already has not been added
                    try
                    {
                        //getting url of image to store in the database
                        URL url = new URL("http://image.tmdb.org/t/p/w500"+particularMovieConstant.getPosterURL());

                        //create object of FavAsync class to store the data in database
                        FavAsync async = new FavAsync();

                        //executing asynctask
                        async.execute(url);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //displaying toast message
                    Toast.makeText(MovieDetailScreen.this, "Movie added to your favoriate movie list", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //onclick of optionbtn1 button of toolbar
        optionbtn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //displaying toast message
                Toast.makeText(MovieDetailScreen.this,"Press back button to see the options",Toast.LENGTH_SHORT).show();
            }
        });

        //onclick of optionbtn2 button of toolbar
        optionbtn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //displaying toast message
                Toast.makeText(MovieDetailScreen.this,"Press back button to see the options",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //MovieDetails class to show all the movie details
    public class MovieDetails implements HandleJsonListener
    {

        //constuctor
        public MovieDetails()
        {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(MovieDetailScreen.this,MovieDetailURL,this);

            //executing the interface
            if(networkRequestTask.getStatus()!=null)
            {
                networkRequestTask.execute();
            }
            else
            {
                networkRequestTask.execute();
                try {
                    networkRequestTask.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //implementing the updateData method to get the movie deatils
        @Override
        public void updateData(String json)
        {
            float popularity=0;

            try {

                //creating Json object
                JSONObject object = new JSONObject(json);

                //storing respective fileds of json object in particularMovieConstant object
                particularMovieConstant.setAverageVote(object.getString("vote_average"));
                particularMovieConstant.setBudget(object.getString("budget"));
                particularMovieConstant.setDescription(object.getString("tagline"));
                particularMovieConstant.setmName(object.getString("original_title"));
                popularity = Float.parseFloat(object.getString("popularity"))/100;
                if(popularity>5)
                {
                    popularity = 5;
                }
                particularMovieConstant.setPopularity(popularity);
                particularMovieConstant.setPosterURL(object.getString("poster_path"));
                particularMovieConstant.setReleaseDate(object.getString("release_date"));
                particularMovieConstant.setRevenue(object.getString("revenue"));
                particularMovieConstant.setStatus(object.getString("status"));
                particularMovieConstant.setVoteCount(object.getString("vote_count"));
                particularMovieConstant.setOverview(object.getString("overview"));

                //setting image into imageview getting from json object
                Glide.with(MovieDetailScreen.this).load("http://image.tmdb.org/t/p/w500"+particularMovieConstant.getPosterURL())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(moviePoster);

                //setting other movie details into their respective fields
                Title.setText(particularMovieConstant.getmName());
                Description.setText("Tag Line : "+particularMovieConstant.getDescription());
                ReleaseDate.setText("Release Date : "+particularMovieConstant.getReleaseDate());
                Budget.setText("Budget : "+particularMovieConstant.getBudget());
                Revenue.setText("Revenue : "+particularMovieConstant.getRevenue());
                Status.setText("Status : "+particularMovieConstant.getStatus());
                VotesCount.setText(particularMovieConstant.getVoteCount() +" users ..");
                AverageVotes.setText("("+particularMovieConstant.getAverageVote()+"/10)");
                Overview.setText(particularMovieConstant.getOverview());
                multipleRating.setRating(particularMovieConstant.getPopularity());
                singleRating.setRating(particularMovieConstant.getPopularity()/5);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //PosterSet class to show all the movie posters
    public class PosterSet implements HandleJsonListener
    {

        //constructor
        public PosterSet()
        {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(MovieDetailScreen.this,PosterURL,this);

            //executing the interface
            if(networkRequestTask.getStatus()!=null)
            {
                networkRequestTask.execute();
            }
            else
            {
                networkRequestTask.execute();
                try {
                    networkRequestTask.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //implementing the updateData method to get the all movie posters
        @Override
        public void updateData(String json)
        {
            try
            {
                //creating parent Json object
                JSONObject parentobject = new JSONObject(json);

                //getting array of Json objects in name posters
                JSONArray array = parentobject.getJSONArray("posters");

                //loop for adding whole array into arraylist
                for(int i=0;i<array.length();i++)
                {
                    //creating child Json object to access child array
                    JSONObject childobject = array.getJSONObject(i);

                    //creating object of Posters class to add in arraylist
                    Posters posters = new Posters();

                    //storing respective posters of json array in posters object
                    posters.setPostersUrl(childobject.getString("file_path"));

                    //adding object of posters type into arraylist
                    mposters.add(posters);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Creating object of LinearLayoutManager and setting to recyclerView.
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

            //setting layout manager to poster Recyclerview
            poster.setLayoutManager(linearLayoutManager);
            //posterAdapter object to show initialList into poster recyclerview
            PosterAdapter posterAdapter = new PosterAdapter(MovieDetailScreen.this,mposters);

            //if data is getting changed of poster recyclerview
            posterAdapter.notifyDataSetChanged();

            //setting adapter to poster recyclerview
            poster.setAdapter(posterAdapter);
        }

    }//end of Posters class

    //CastAndCrewSet class to show all the cast and crew details of the movie
    public class CastAndCrewSet implements HandleJsonListener
    {

        //constructor
        public CastAndCrewSet()
        {
            //creating interface object
            NetworkRequestTask networkRequestTask = new NetworkRequestTask(MovieDetailScreen.this,CastAndCrewURL,this);

            //executing the interface
            if(networkRequestTask.getStatus()!=null)
            {
                networkRequestTask.execute();
            }
            else
            {
                networkRequestTask.execute();
                try {
                    networkRequestTask.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //implementing the updateData method to get the all cast and crew details of the movie
        @Override
        public void updateData(String json)
        {
            try
            {
                //creating parent Json object
                JSONObject parentobject = new JSONObject(json);

                //getting array of Json objects in name cast
                JSONArray arraycast = parentobject.getJSONArray("cast");

                //getting array of Json objects in name crew
                JSONArray arraycrew = parentobject.getJSONArray("crew");

                //loop for adding whole cast array into arraylist
                for(int i=0;i<arraycast.length();i++)
                {
                    //creating child Json object to access child array
                    JSONObject childobject = arraycast.getJSONObject(i);

                    //creating object of Cast class to add in arraylist
                    CastMember cast = new CastMember();

                    //storing respective fields of json array in cast object
                    cast.setPicURL(childobject.getString("profile_path"));
                    cast.setCastMovieName(childobject.getString("character"));
                    cast.setCastOriginalName(childobject.getString("name"));

                    //adding object of cast type into arraylist
                    mCast.add(cast);
                }

                //loop for adding whole crew array into arraylist
                for(int i=0;i<arraycrew.length();i++)
                {
                    //creating child Json object to access child array
                    JSONObject childobject = arraycrew.getJSONObject(i);

                    //creating object of Crew class to add in arraylist
                    CrewMember crew = new CrewMember();

                    //storing respective fields of json array in crew object
                    crew.setCrewPic(childobject.getString("profile_path"));
                    crew.setCrewName(childobject.getString("name"));
                    crew.setCrewJob(childobject.getString("job"));

                    //adding object of crew type into arraylist
                    mCrew.add(crew);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Creating object of LinearLayoutManager and setting to Toalcast recyclerView.
            RecyclerView.LayoutManager linearLayoutManager2=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

            //setting layout manager to Toalcast recyclerview
            Toalcast.setLayoutManager(linearLayoutManager2);

            //castAdapter object to show initialList into Toalcast recyclerview
            CastAdapter castAdapter = new CastAdapter(MovieDetailScreen.this,mCast);

            //setting adapter to Toalcast recyclerView
            Toalcast.setAdapter(castAdapter);

            //Creating object of LinearLayoutManager and setting to Totalcrew recyclerView.
            LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);

            //setting layout manager to  Totalcrew recyclerview
            Totalcrew.setLayoutManager(linearLayoutManager1);

            //crewAdapter object to show initialList into Totalcrew recyclerview
            CrewAdapter crewAdapter = new CrewAdapter(mCrew,MovieDetailScreen.this);

            //setting adapter to Totalcrew recyclerView
            Totalcrew.setAdapter(crewAdapter);

        }

    }//end of castandcrew class

    //class FavAsync to store the movie details in favoriate movie list table in database
    public class FavAsync extends AsyncTask<URL, Void, Bitmap>
    {

        //doInBackground method
        @Override
        protected Bitmap doInBackground(URL... urls)
        {
            URLConnection connection = null;

            try
            {
                //getting url connection
                connection = urls[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            connection.setDoInput(true);

            try
            {
                //connecting with server
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream inputStream = null;

            try
            {
                //getting image inputStream from server
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            //getting bitmap image from server
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            return bitmap;

        }//end of doInBackground method

        //onPostExecute method
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);

            //creating storingData object to store information in database
            StoringDataConstants storingData = new StoringDataConstants();

            //storing data in their respective fields
            storingData.setMovieId(id);
            storingData.setPopularity(particularMovieConstant.getPopularity().toString());
            storingData.setReleasedate(particularMovieConstant.getReleaseDate());
            storingData.setStorename(particularMovieConstant.getmName());
            storingData.setVoteAverage(particularMovieConstant.getAverageVote());
            storingData.setVotecount(particularMovieConstant.getVoteCount());
            storingData.setStoredimage(bitmap);

            //adding details in the favlist table in database
            dataBaseHelper2.addFavEntry(storingData);

        }//end of onPostExecute method

    }//end of FavAsync class

    //class WatchlistAsync to store the movie details in watchlist movie list table in database
    public class WatchlistAsync extends AsyncTask<URL, Void, Bitmap>
    {

        //doInBackground method
        @Override
        protected Bitmap doInBackground(URL... urls)
        {
            URLConnection connection = null;

            try
            {
                //getting url connection
                connection = urls[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            connection.setDoInput(true);

            try
            {
                //connecting with server
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream inputStream = null;

            try
            {
                //getting image inputStream from server
                inputStream = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            //getting bitmap image from server
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            return bitmap;

        }//end of doInBackground method

        //onPostExecute method
        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);

            //creating storingData object to store information in database
            StoringDataConstants storingData = new StoringDataConstants();

            //storing data in their respective fields
            storingData.setMovieId(id);
            storingData.setPopularity(particularMovieConstant.getPopularity().toString());
            storingData.setReleasedate(particularMovieConstant.getReleaseDate());
            storingData.setStorename(particularMovieConstant.getmName());
            storingData.setVoteAverage(particularMovieConstant.getAverageVote());
            storingData.setVotecount(particularMovieConstant.getVoteCount());
            storingData.setStoredimage(bitmap);

            //adding details in the watchlist table in database
            dataBaseHelper1.addWatchEntry(storingData);

        }//end of onPostExecute method

    }//end of WatchlistAsync class

}//end of class
