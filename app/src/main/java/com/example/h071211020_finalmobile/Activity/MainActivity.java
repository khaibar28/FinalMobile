package com.example.h071211020_finalmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.h071211020_finalmobile.Fragment.FavoritesFragment;
import com.example.h071211020_finalmobile.Fragment.MoviesFragment;
import com.example.h071211020_finalmobile.Fragment.TvShowsFragment;
import com.example.h071211020_finalmobile.R;
import com.example.h071211020_finalmobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    MoviesFragment moviesFragment =new MoviesFragment();
    TvShowsFragment tvShowsFragment =new TvShowsFragment();
    FavoritesFragment favoritesFragment =new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager =getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(MoviesFragment.class.getSimpleName());

        if(!(fragment instanceof MoviesFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, moviesFragment,MoviesFragment.class.getSimpleName())
                    .commit();
            binding.toolbar.setText("Movies");

        }

        binding.btnMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, moviesFragment).commit();
                binding.toolbar.setText("Movies");
            }
        });


        binding.btnTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, tvShowsFragment).commit();
                binding.toolbar.setText("TV Show");
            }
        });

        binding.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, favoritesFragment).commit();
                binding.toolbar.setText("Favorite");
            }
        });
    }
}