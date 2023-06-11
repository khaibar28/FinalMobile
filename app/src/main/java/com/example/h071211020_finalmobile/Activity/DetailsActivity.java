package com.example.h071211020_finalmobile.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.h071211020_finalmobile.Database.DatabaseHelper;
import com.example.h071211020_finalmobile.Database.ModelHelper;
import com.example.h071211020_finalmobile.Model.MovieModel;
import com.example.h071211020_finalmobile.Model.TvModel;
import com.example.h071211020_finalmobile.R;
import com.example.h071211020_finalmobile.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra-movie";
    public static final String EXTRA_TV = "extra-tv";

    private ActivityDetailsBinding binding;
    private DatabaseHelper databaseHelper;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = DatabaseHelper.getInstance(this);

        TvModel tvModel = getIntent().getParcelableExtra(EXTRA_TV);
        MovieModel movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (tvModel != null) {
            showTvDetails(tvModel);
        } else if (movieModel != null) {
            showMovieDetails(movieModel);
        }

        binding.cvBack.setOnClickListener(view -> onBackPressed());
    }

    private void showTvDetails(TvModel tvModel) {
        binding.tvNamaFilm.setText(tvModel.getOriginal_name());
        binding.tvDate.setText(tvModel.getFirst_air_date());
        binding.tvOverview.setText(tvModel.getOverview());
        binding.tvRate.setText(tvModel.getVote_average());
        binding.ivType.setImageResource(R.drawable.baseline_tv_24);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getPoster_path()).into(binding.ivPosterPath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getBackdrop_path()).into(binding.ivBackdropPath);

        isFavorite = ModelHelper.isFavoriteTv(this, tvModel.getId());
        updateFavoriteIcon();

        binding.cvFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                removeFromFavorites(tvModel);
            } else {
                addToFavorites(tvModel);
            }
        });
    }

    private void showMovieDetails(MovieModel movieModel) {
        binding.tvNamaFilm.setText(movieModel.getOriginal_title());
        binding.tvDate.setText(movieModel.getRelease_date());
        binding.tvOverview.setText(movieModel.getOverview());
        binding.tvRate.setText(movieModel.getVote_average());
        binding.ivType.setImageResource(R.drawable.baseline_movie_24);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getPoster_path()).into(binding.ivPosterPath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getBackdrop_path()).into(binding.ivBackdropPath);

        isFavorite = ModelHelper.isFavoriteMovie(this, movieModel.getId());
        updateFavoriteIcon();

        binding.cvFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                removeFromFavorites(movieModel);
            } else {
                addToFavorites(movieModel);
            }
        });
    }

    private void addToFavorites(TvModel tvModel) {
        if (databaseHelper != null) {
            ModelHelper.insertTv(this, tvModel);
            isFavorite = true;
            updateFavoriteIcon();
        }
    }

    private void addToFavorites(MovieModel movieModel) {
        if (databaseHelper != null) {
            ModelHelper.insertMovie(this, movieModel);
            isFavorite = true;
            updateFavoriteIcon();
        }
        // Tambahkan logika lain yang diinginkan setelah input ke database
    }

    private void removeFromFavorites(TvModel tvModel) {
        if (databaseHelper != null) {
            ModelHelper.deleteTv(this, tvModel.getId());
            isFavorite = false;
            updateFavoriteIcon();
        }
    }

    private void removeFromFavorites(MovieModel movieModel) {
        if (databaseHelper != null) {
            ModelHelper.deleteMovie(this, movieModel.getId());
            isFavorite = false;
            updateFavoriteIcon();
        }
    }

    private void updateFavoriteIcon() {
        if (isFavorite) {
            binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            binding.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
        }
    }
}
