package com.example.aavgeensingh.moviac.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aavgeensingh.moviac.MovieView;
import com.example.aavgeensingh.moviac.R;
import com.example.aavgeensingh.moviac.movies;
import com.example.aavgeensingh.moviac.topRated;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Aavgeen Singh on 3/31/2017.
 */

public class movieListAdapter extends RecyclerView.Adapter<movieListAdapter.movieViewHolder> {
    ArrayList<movies> mvs;
    private Context context;
    topRated tr;
    int i=1;
    int highestI=1;

    public movieListAdapter(Context context, ArrayList<movies> mvs, topRated tr) {
        this.context = context;
        this.mvs = mvs;
        this.tr=tr;
    }

    public movieListAdapter(ArrayList<movies> mvs){
        this.mvs=mvs;
    }
    @Override
    public movieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new movieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(movieViewHolder holder, int position) {
        movies m= mvs.get(position);
        if(( position >= (highestI*20 -10))){
            tr.fetchMovies(++i);
        }
        if(i>=highestI)
            highestI=i;
        holder.number.setText((position+1)+"");
        holder.title.setText(m.getTitle());
        holder.releaseDate.setText(m.getReleaseDate());
        //genres
        holder.voteAverage.setText(m.getVoteAverage()+"");
        String url=m.getBackdropPath();
        Picasso.with(this.context)   // doubt in context thing.
                .load("http://image.tmdb.org/t/p/w185"+url)
                .placeholder(R.drawable.loadbar)   // optional
                .error(R.drawable.ic_date_range_black_24dp)
                .into(holder.backdrop);
    }

    @Override
    public int getItemCount() {
        return mvs.size();
    }



    public class movieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,releaseDate,genres,voteAverage, number;
        ImageView backdrop;
        public movieViewHolder(View itemView) {
            super(itemView);
            number=(TextView)itemView.findViewById(R.id.number);
            title=(TextView) itemView.findViewById(R.id.title);
            releaseDate=(TextView) itemView.findViewById(R.id.releaseDate);
            genres=(TextView) itemView.findViewById(R.id.genres);
            voteAverage=(TextView) itemView.findViewById(R.id.voteAverage);
            backdrop=(ImageView) itemView.findViewById(R.id.backdrop);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,"Clicked man",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(context, MovieView.class);
            //i.putExtra("MovieDetail", (Serializable) item);
            //startActivity(i);
        }
    }


}
