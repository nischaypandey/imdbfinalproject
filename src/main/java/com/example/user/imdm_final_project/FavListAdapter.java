package com.example.user.imdm_final_project;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

//FavListAdapter class which is extending RecyclerView class to show list of favoriate list and watchlist Movie list
public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.VersionViewHolder>
{
    //reference of ArrayList.
    ArrayList<StoringDataConstants> versionList;

    //reference of context
    public Context mContext;


    //constructor
    public FavListAdapter(ArrayList<StoringDataConstants> versionList, Context mContext)
    {
        this.versionList = versionList;
        this.mContext = mContext;
    }


    //onCreateViewHolder method
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflating item_raw Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        //making object of  VersionViewHolder class
        VersionViewHolder tempObj = new VersionViewHolder(view);

        return tempObj;
    }

    //onBindViewHolder method
    @Override
    public void onBindViewHolder(FavListAdapter.VersionViewHolder holder, int position)
    {



        holder.posterName.setImageBitmap(versionList.get(position).getStoredimage());
        float popularity =0;

        popularity = Float.parseFloat(versionList.get(position).getPopularity());

        if(popularity>5)
        {
            popularity=5;
        }

        //setting other movie details stored in the database to layout objects
        holder.movieName.setText(versionList.get(position).getStorename());
        holder.releaseDate.setText("Release Date : "+versionList.get(position).getReleasedate());
        holder.ratingBar.setRating(popularity);
        holder.singleStarRatingBar.setRating(Float.parseFloat(versionList.get(position).getVoteAverage())/10);
        holder.voteAverage.setText("("+versionList.get(position).getVoteAverage()+"/10) voted by "+versionList.get(position).getVotecount() +" users...");
    }

    //getItemCount method which is returning size of the list
    @Override
    public int getItemCount()
    {
        return versionList.size();
    }

    //class VersionViewHolder show favmovie list
    public class VersionViewHolder extends RecyclerView.ViewHolder
    {

        //creating references of CardView and textView.
        CardView cardView;
        ImageView posterName;
        TextView movieName;
        TextView releaseDate;
        RatingBar ratingBar,singleStarRatingBar;
        TextView voteAverage;

        public VersionViewHolder(View itemView)
        {
            super(itemView);

            //Setting references with their IDs.
            cardView=itemView.findViewById(R.id.card_view);
            posterName=itemView.findViewById(R.id.list_image_iv);
            movieName=itemView.findViewById(R.id.movie_name);
            releaseDate=itemView.findViewById(R.id.release_date);
            ratingBar=itemView.findViewById(R.id.rating);
            voteAverage=itemView.findViewById(R.id.vote_average);
            singleStarRatingBar=itemView.findViewById(R.id.single_star);
        }
    }
}