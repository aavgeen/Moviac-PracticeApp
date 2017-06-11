package com.example.aavgeensingh.moviac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

import com.example.aavgeensingh.moviac.RecyclerAdapters.movieListAdapter;
import com.example.aavgeensingh.moviac.network.apiInterface;
import com.example.aavgeensingh.moviac.network.apiInterfacePopular;
import com.example.aavgeensingh.moviac.network.movieResponse;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link topRated.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link topRated#newInstance} factory method to
 * create an instance of this fragment.
 */
public class topRated extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView homeList;
    ArrayList<movies> movie;
    movieListAdapter listAdapter;
    CardView movieCard;
    Space spaceExpand;
    static boolean reload=false;
    String pageType;

    final static String BASE_URL="https://api.themoviedb.org/3/";
    public topRated() {
       //default empty required.
    }


    public static topRated newInstance(String type) {
        topRated fragment = new topRated();
        fragment.pageType=type;
        Bundle args = new Bundle();
        //args.putString("PageType",type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_top_rated, container, false);


        homeList=(RecyclerView)v.findViewById(R.id.topRated);
        movie=new ArrayList<>();
        listAdapter=new movieListAdapter(getActivity(),movie,this);

        RecyclerView.LayoutManager lm=new LinearLayoutManager(getActivity());
        homeList.setLayoutManager(lm);
        homeList.setAdapter(listAdapter);
        movieCard=(CardView)v.findViewById(R.id.movieCard);
        spaceExpand=(Space)v.findViewById(R.id.spaceExpand);
        homeList.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(getActivity(),MovieView.class);
                        i.putExtra("MovieDetail", (Serializable) movie.get(position));
                        startActivity(i);
                    }
                })
        );

        fetchMovies(1);
        return v;
    }

    public void fetchMovies(int pNo) {//String pNo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .build();
        apiInterface apiI;
        apiInterfacePopular apiIPopular;
            apiI= retrofit.create(apiInterface.class);
           apiIPopular= retrofit.create(apiInterfacePopular.class);

        Call<movieResponse> call;//pNo
        if(pageType == "topRated")
            call =apiI.getResults(pNo); //pNo
        else {
            call =apiIPopular.getResults(pNo);
        }

        call.enqueue(new Callback<movieResponse>() {
            @Override
            public void onResponse(Call<movieResponse> call, Response<movieResponse> response) {
                if(response.isSuccessful()){
                    Log.e("See this bro",response.message()+response.code());
                    movieResponse mRes= response.body();
                    ArrayList<movies> temp;
                    temp=mRes.getResults();
                    //movie.clear();
                    movie.addAll(temp);
                    System.out.println(""+pageType+" has size -  "+movie.size());
                    listAdapter.notifyDataSetChanged();
                    reload=true;
                }
            }

            @Override
            public void onFailure(Call<movieResponse> call, Throwable t) {

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
