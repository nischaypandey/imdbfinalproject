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

//CastAdapter class which is showing cast details and extending RecyclerView class
public class CastAdapter extends RecyclerView.Adapter<CastAdapter.VersionViewHolder>
{
    //creating arraylist object
    public ArrayList<CastMember> mCast;

    //creating instance of context
    public Context mContext;

    //contructor
    public CastAdapter(Context context,ArrayList<CastMember> casts)
    {
        //getting context into mContext
        mContext = context;

        //getting list of casts into mCast list
        mCast = casts;
    }

    //onCreateViewHolder method
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //inflating item_raw Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_card_view, parent, false);

        //making object of  VersionViewHolder class
        CastAdapter.VersionViewHolder tempObj = new VersionViewHolder(view);

        return tempObj;
    }

    //onBindViewHolder method
    @Override
    public void onBindViewHolder(CastAdapter.VersionViewHolder holder, int position)
    {
        //setting cast image into imageview
        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500"+mCast.get(position).getPicURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.by_default)
                .into(holder.imageView);

        //setting cast (movie name) into mMovieName
        holder.mMovieName.setText(mCast.get(position).getCastMovieName());

        //setting cast (original name) into moriginalName
        holder.moriginalName.setText("( "+mCast.get(position).getCastOriginalName()+" )");
    }

    //getItemCount method which is returning size of the list
    @Override
    public int getItemCount() {
        return mCast.size();
    }

    //class VersionViewHolder show cast list
    public class VersionViewHolder extends RecyclerView.ViewHolder
    {
        //initialising values
        CardView cardView;
        ImageView imageView;
        TextView mMovieName;
        TextView moriginalName;

        //constructor
        public VersionViewHolder(View itemView)
        {
            super(itemView);

            //setting reference with their ID's
            cardView = itemView.findViewById(R.id.cast_card_view);
            imageView = itemView.findViewById(R.id.cast_pic);
            mMovieName = itemView.findViewById(R.id.cast_movie_name);
            moriginalName = itemView.findViewById(R.id.cast_original_name);
        }
    }
}
