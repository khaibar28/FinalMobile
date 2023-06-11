package com.example.h071211020_finalmobile.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.h071211020_finalmobile.Fragment.FavoritesFragment;
import com.example.h071211020_finalmobile.Fragment.MoviesFragment;
import com.example.h071211020_finalmobile.Fragment.TvShowsFragment;
import com.example.h071211020_finalmobile.R;
import com.example.h071211020_finalmobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private MoviesFragment moviesFragment = new MoviesFragment();
    private TvShowsFragment tvShowsFragment = new TvShowsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        displayFragment();
        showProgressBar(true);

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

        checkInternetConnectivity();

        binding.ivNoInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternetConnectivity();
            }
        });
    }

    private void checkInternetConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = null;
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            capabilities = connectivityManager.getNetworkCapabilities(network);
        }

        if (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
            // Internet is available
            showProgressBar(false);
            Toast.makeText(MainActivity.this, "Internet is available", Toast.LENGTH_SHORT).show();
            displayFragment();
        } else {
            // No internet connection
            showProgressBar(false);
            showNoInternet();
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayFragment() {

            Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame_container);

            if (!(currentFragment instanceof MoviesFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, moviesFragment, MoviesFragment.class.getSimpleName())
                        .commit();
                binding.toolbar.setText("Movies");
            } else if (!(currentFragment instanceof TvShowsFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, tvShowsFragment, TvShowsFragment.class.getSimpleName())
                        .commit();
                binding.toolbar.setText("TV Show");
            } else if (!(currentFragment instanceof FavoritesFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, favoritesFragment, FavoritesFragment.class.getSimpleName())
                        .commit();
                binding.toolbar.setText("Favorite");

        }

    }

    private void showProgressBar(boolean isLoading) {
        binding.tvNoInternet.setVisibility(View.GONE);
        if (isLoading) {
            binding.progress.setVisibility(View.VISIBLE);
            binding.frameContainer.setVisibility(View.GONE);
        } else {
            binding.progress.setVisibility(View.GONE);
            binding.frameContainer.setVisibility(View.VISIBLE);
        }
    }

    private void showNoInternet() {
        binding.tvNoInternet.setVisibility(View.VISIBLE);
        binding.ivNoInternet.setVisibility(View.VISIBLE);
        binding.frameContainer.setVisibility(View.GONE);
        binding.progress.setVisibility(View.GONE);
    }
}
