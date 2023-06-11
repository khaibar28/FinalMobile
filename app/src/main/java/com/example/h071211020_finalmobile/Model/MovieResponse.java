package com.example.h071211020_finalmobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    public List<MovieModel> movieModels;

    public List<MovieModel> getMovieModels() {return movieModels;}
}
