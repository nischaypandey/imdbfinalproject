package com.example.user.imdm_final_project;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;


//CrewAdapter class which is showing crew details and extending RecyclerView class
public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.VersionViewHolder>
{
    //creating arraylist object
    public ArrayList<CrewMember> mCrew;

    //creating instance of context
    public Context mContext;

    //constructor
    public CrewAdapter(ArrayList<CrewMember> Crew, Context context)
    {
        //getting list of casts into mCast list
        mCrew = Crew;

        //getting context into mContext
        mContext = context;
    }

    //onCreateViewHolder method
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflating item_raw Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crew_card_view, parent, false);

        //making object of  VersionViewHolder class
        CrewAdapter.VersionViewHolder tempObj = new VersionViewHolder(view);

        return tempObj;

    }

    //onBindViewHolder method
    @Override
    public void onBindViewHolder(CrewAdapter.VersionViewHolder holder, int position)
    {
        //setting crew image into imageview
        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500"+mCrew.get(position).getCrewPic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.by_default)
                .into(holder.imageView);

        //setting crew (original name) into mName
        holder.mName.setText(mCrew.get(position).getCrewName());

        //setting crew job in making the movie
        holder.mJob.setText(mCrew.get(position).getCrewJob());
    }

    //getItemCount method which is returning size of the list
    @Override
    public int getItemCount()
    {
        return mCrew.size();
    }

    //class VersionViewHolder show cast list
    public class VersionViewHolder extends RecyclerView.ViewHolder
    {
        //initialising values
        CardView cardView;
        ImageView imageView;
        TextView mName;
        TextView mJob;

        //constructor
        public VersionViewHolder(View itemView)
        {
            super(itemView);

            //setting reference with their ID's
            cardView = itemView.findViewById(R.id.crew_card_view);
            imageView = itemView.findViewById(R.id.crew_pic);
            mName = itemView.findViewById(R.id.crew_movie_name);
            mJob = itemView.findViewById(R.id.crew_original_name);
        }
    }
}
