package com.example.aavgeensingh.moviac.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Aavgeen Singh on 3/31/2017.
 */

public interface apiInterface {
    @GET("movie/top_rated?api_key=73abb06c1d1779d7b404acd5da25d731")//{pNo}
    Call<movieResponse> getResults(@Query("page") int i);   //
}
