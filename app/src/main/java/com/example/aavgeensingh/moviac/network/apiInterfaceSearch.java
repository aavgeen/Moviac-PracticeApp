package com.example.aavgeensingh.moviac.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aavgeen Singh on 4/14/2017.
 */

public interface apiInterfaceSearch {
    @GET("search/movie?api_key=73abb06c1d1779d7b404acd5da25d731")
    Call<movieResponse> getResults(@Query("query") String i);
}
