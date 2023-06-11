package com.example.h071211020_finalmobile.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211020_finalmobile.Database.DatabaseHelper;
import com.example.h071211020_finalmobile.Activity.DetailsActivity;
import com.example.h071211020_finalmobile.Adapter.FavoritesAdapter;
import com.example.h071211020_finalmobile.Model.MovieModel;
import com.example.h071211020_finalmobile.Model.TvModel;
import com.example.h071211020_finalmobile.databinding.FragmentFavoritesBinding;

import java.util.List;

public class FavoritesFragment extends Fragment implements FavoritesAdapter.OnItemClickListener {

    private FragmentFavoritesBinding binding;
    private FavoritesAdapter favoritesAdapter;
    private List<MovieModel> favoriteMovies;
    private List<TvModel> favoriteTVs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteMovies = DatabaseHelper.getInstance(getContext()).getAllFavoriteMovies();
        favoriteTVs = DatabaseHelper.getInstance(getContext()).getAllFavoriteTVs();

        favoritesAdapter = new FavoritesAdapter(favoriteMovies, favoriteTVs);
        favoritesAdapter.setOnItemClickListener(this);

        RecyclerView recyclerView = binding.rvFavorite;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(favoritesAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        favoriteMovies = DatabaseHelper.getInstance(getContext()).getAllFavoriteMovies();
        favoriteTVs = DatabaseHelper.getInstance(getContext()).getAllFavoriteTVs();
        favoritesAdapter.updateData(favoriteMovies, favoriteTVs);
    }

    @Override
    public void onItemClick(MovieModel movie) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onItemClick(TvModel tv) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_TV, tv);
        startActivity(intent);
    }
}
