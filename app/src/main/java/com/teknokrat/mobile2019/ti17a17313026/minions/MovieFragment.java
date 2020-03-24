package com.teknokrat.mobile2019.ti17a17313026.minions;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvMovies = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_list);
        rvMovies.setHasFixedSize(true);
        list.addAll(getListMovies());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvMovies.setAdapter(movieAdapter);
        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie movie) {
                showSelectedMovie(movie);
            }
        });
    }

    private ArrayList<Movie> getListMovies() {
        String[] movieName = getResources().getStringArray(R.array.data_movie);
        String[] movieDescription = getResources().getStringArray(R.array.movie_description);
        @SuppressLint("Recycle") TypedArray moviePhoto = getResources().obtainTypedArray(R.array.movie_photo);
        String[] movieGenre = getResources().getStringArray(R.array.movie_genre);
        String[] movieRelease = getResources().getStringArray(R.array.release_movie);
        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < movieName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(moviePhoto.getResourceId(i, -1));
            movie.setName(movieName[i]);
            movie.setDescription(movieDescription[i]);
            movie.setGenre(movieGenre[i]);
            movie.setRelease(movieRelease[i]);
            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showSelectedMovie(Movie movie) {
        Intent detailActivity = new Intent(this.getActivity(), DetailActivity.class);
        detailActivity.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(detailActivity);
    }
}
