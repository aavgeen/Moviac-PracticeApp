package com.example.aavgeensingh.moviac.network;

import java.util.ArrayList;

import com.example.aavgeensingh.moviac.movies;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Aavgeen Singh on 3/31/2017.
 */

public class movieResponse {
    int page;
    ArrayList<movies> results;

    public ArrayList<movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<movies> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
