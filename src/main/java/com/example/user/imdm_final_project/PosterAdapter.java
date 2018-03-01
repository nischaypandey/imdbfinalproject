package com.example.user.imdm_final_project;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;

//PosterAdapter class which is showing posters and extending RecyclerView class
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.VersionViewHolder>
{
    //creating arraylist object
    public  ArrayList<Posters> mPosters;

    //creating instance of context
    public Context mContext;

    //contructor
    public PosterAdapter(Context context, ArrayList<Posters> posters)
    {
        mContext = context;
        mPosters = posters;
    }

    //onCreateViewHolder method
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflating item_raw Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_card_view, parent, false);

        //making object of  VersionViewHolder class
        PosterAdapter.VersionViewHolder tempObj = new VersionViewHolder(view);

        return tempObj;
    }

    //onBindViewHolder method
    @Override
    public void onBindViewHolder(PosterAdapter.VersionViewHolder holder, int position)
    {
        //setting poster image into imageview
        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500"+mPosters.get(position).getPostersUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.by_default)
                .into(holder.imageView);

    }

    //getItemCount method which is returning size of the list
    @Override
    public int getItemCount()
    {
        return mPosters.size();
    }

    //class VersionViewHolder show poster list
    public class VersionViewHolder extends RecyclerView.ViewHolder
    {
        //initialising values
        CardView cardView;
        ImageView imageView;

        //constructor
        public VersionViewHolder(View itemView)
        {
            super(itemView);
            //setting reference with their ID's
            cardView = itemView.findViewById(R.id.poster_card_view);
            imageView = itemView.findViewById(R.id.poster_pic);
        }
    }
}
