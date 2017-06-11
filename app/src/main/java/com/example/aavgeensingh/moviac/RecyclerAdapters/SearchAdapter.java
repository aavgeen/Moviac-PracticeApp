package com.example.aavgeensingh.moviac.RecyclerAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aavgeensingh.moviac.MovieView;
import com.example.aavgeensingh.moviac.R;
import com.example.aavgeensingh.moviac.movies;

import java.util.ArrayList;

/**
 * Created by Aavgeen Singh on 4/14/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchResultHolder> {
    ArrayList<movies> movieNames;
    Context context;
    public SearchAdapter(Context context, ArrayList<movies> movieNames){
        this.context=context;
        this.movieNames=movieNames;
    }
    @Override
    public searchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new searchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.searchResultHolder holder, int position) {
        movies name=movieNames.get(position);
        holder.movieName.setText(name.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieNames.size();
    }


    public class searchResultHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        public searchResultHolder(View itemView) {
            super(itemView);
            movieName=(TextView)itemView.findViewById(android.R.id.text1);
        }
    }
}
