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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;

//RVAdapter class which is showing movie details and extending RecyclerView class
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VersionViewHolder>
{
    //referencee of ArrayList.
    ArrayList<Constants> versionList;

    //creating instance of context
    public Context mContext;

    //creating instance of listener ItemClicked
    private ItemClicked itemClicked;

    //Constructor of RVAdapter
    public RVAdapter(ArrayList<Constants> list, Context context) {
        versionList = list;
        mContext=context;
    }

    //overrriden methods
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflating item_raw Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        //making object of  VersionViewHolder class
        VersionViewHolder tempObj = new VersionViewHolder(view);

        return tempObj;
    }

    //overriden method
    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {

        //setting cast image into imageview
        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500"+versionList.get(position).getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.by_default)
                .into(holder.posterName);

        //setting other movie details into their respective fields
        holder.movieName.setText(versionList.get(position).getName());
        holder.releaseDate.setText("Release Date : "+versionList.get(position).getReleaseDate());
        holder.ratingBar.setRating(versionList.get(position).getPolpularity());
        holder.singleStarRatingBar.setRating(Float.parseFloat(versionList.get(position).getVotesAverage())/10);
        holder.voteAverage.setText("("+versionList.get(position).getVotesAverage()+"/10) voted by "+versionList.get(position).getVotesCount() +" users...");
    }

    //getItemCount method which is returning size of the list
    @Override
    public int getItemCount() {
        return versionList.size();
    }

    //implementing setClickListener method on click of any item of recyclerView
    public void setClickListener(ItemClicked item)
    {
        itemClicked = item;
    }

    //Creating nested class by extending RecyclerView.ViewHolder.
    public  class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //creating references of CardView and textView.
        CardView cardView;
        ImageView posterName;
        TextView movieName;
        TextView releaseDate;
        RatingBar ratingBar,singleStarRatingBar;
        TextView voteAverage;


        //constructor.
        VersionViewHolder(View itemView)
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

            //calling setOnClickListener on click of any item
            itemView.setOnClickListener(this);
        }

        //overriding onClick method
        @Override
        public void onClick(View view) {
            if(itemClicked!=null)
            {
                itemClicked.onCLick(view,getAdapterPosition());
            }
        }
    }
}
