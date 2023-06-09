package com.example.h071211020_finalmobile.Networking;

import com.example.h071211020_finalmobile.Model.MovieResponse;
import com.example.h071211020_finalmobile.Model.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("movie/popular?api_key=fd94cf72366ab24dd8c3ddb41fcf9650")
    Call<MovieResponse> getListMovie();

    @GET("tv/popular?api_key=fd94cf72366ab24dd8c3ddb41fcf9650")
    Call<TvResponse> getListTv();

}
