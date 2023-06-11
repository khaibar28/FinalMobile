package com.example.h071211020_finalmobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {

    @SerializedName("results")
    public List<TvModel> tvModels;

    public List<TvModel> getTvModels() {return tvModels;}
}
