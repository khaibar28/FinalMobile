package com.example.h071211020_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.h071211020_finalmobile.Model.MovieModel;
import com.example.h071211020_finalmobile.Model.TvModel;
import com.example.h071211020_finalmobile.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra-movie";
    public static final String EXTRA_TV= "extra-tv";


    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TvModel tvModel = getIntent().getParcelableExtra(EXTRA_TV);
        MovieModel movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if(tvModel != null){
            binding.tvNamaFilm.setText(tvModel.getOriginal_name());
            binding.tvDate.setText(tvModel.getFirst_air_date());
            binding.tvOverview.setText(tvModel.getOverview());
            binding.tvRate.setText(tvModel.getVote_average());
            binding.ivType.setImageResource(R.drawable.baseline_tv_24);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getPoster_path()).into(binding.ivPosterPath);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getBackdrop_path()).into(binding.ivBackdropPath);
        }else {
            binding.tvNamaFilm.setText(movieModel.getOriginal_title());
            binding.tvDate.setText(movieModel.getRelease_date());
            binding.tvOverview.setText(movieModel.getOverview());
            binding.tvRate.setText(movieModel.getVote_average());
            binding.ivType.setImageResource(R.drawable.baseline_movie_24);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getPoster_path()).into(binding.ivPosterPath);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getBackdrop_path()).into(binding.ivBackdropPath);
        }

        binding.cvBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            startActivity(intent);
            finish();
        });
    }
}