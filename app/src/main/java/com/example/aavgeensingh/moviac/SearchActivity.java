package com.example.aavgeensingh.moviac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aavgeensingh.moviac.RecyclerAdapters.SearchAdapter;
import com.example.aavgeensingh.moviac.network.apiInterfaceSearch;
import com.example.aavgeensingh.moviac.network.movieResponse;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchName;
    RecyclerView searchResults;
    SearchAdapter adapter;
    View loading;
    ArrayList<movies> movie;
    final static String BASE_URL="https://api.themoviedb.org/3/";
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        movie=new ArrayList<>();
        searchName = (EditText)findViewById(R.id.searchName);
        searchResults = (RecyclerView)findViewById(R.id.searchResults);
        searchButton=(Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        adapter=new SearchAdapter(this,movie);
        loading =findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(this);
        searchResults.setLayoutManager(lm);
        searchResults.setAdapter(adapter);
        searchResults.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(SearchActivity.this,MovieView.class);
                        i.putExtra("MovieDetail", (Serializable) movie.get(position));
                        startActivity(i);
                    }
                })
        );
        System.out.println("Activiy launched hai");
    }

    @Override
    public void onClick(View v) {
        loading.setVisibility(View.VISIBLE);
        Button b=(Button)v;
        //if(b.getId() == searchButton.getId()){
            fetchMovies();
        //}
    }

    private void fetchMovies() {
        System.out.println("Search Vala fetchMovie hai.");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();
        apiInterfaceSearch api;
        Call<movieResponse> call;
        api=retrofit.create(apiInterfaceSearch.class);
        call=api.getResults(searchName.getText().toString());
        call.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                if(response.isSuccessful()){
                    Log.e("See this bro",response.message()+response.code());
                    movieResponse mRes= response.body();
                    ArrayList<movies> temp;
                    temp=mRes.getResults();
                    movie.clear();
                    movie.addAll(temp);
                    System.out.println("AKAAKKAKAKAKKAAKAKKAKKAKAKKA has size -  "+movie.size());
                    adapter.notifyDataSetChanged();
                    loading.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {
                System.out.println("          Failed bro");
            }
        });
    }
}
