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
public class TvFragment extends Fragment {

    private RecyclerView rvTvShows;
    private ArrayList<TvShows> list = new ArrayList<>();

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvTvShows = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_tv_list);
        rvTvShows.setHasFixedSize(true);
        list.addAll(getListTv());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTvShows.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        TvShowsAdapter tvShowsAdapter = new TvShowsAdapter(list);
        rvTvShows.setAdapter(tvShowsAdapter);
        tvShowsAdapter.setOnItemClickCallback(new TvShowsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShows tvShows) {
                showSelectedTvShows(tvShows);
            }
        });
    }

    private ArrayList<TvShows> getListTv() {
        String[] tvName = getResources().getStringArray(R.array.data_sv_shows);
        String[] tvDescription = getResources().getStringArray(R.array.sv_description);
        @SuppressLint("Recycle") TypedArray tvPhoto = getResources().obtainTypedArray(R.array.sv_photo);
        String[] movieGenre = getResources().getStringArray(R.array.sv_genre);
        String[] movieRelease = getResources().getStringArray(R.array.release_sv);
        ArrayList<TvShows> listTv = new ArrayList<>();
        for (int i = 0; i < tvName.length; i++) {
            TvShows tvShows = new TvShows();
            tvShows.setPhoto(tvPhoto.getResourceId(i, -1));
            tvShows.setName(tvName[i]);
            tvShows.setDescription(tvDescription[i]);
            tvShows.setGenre(movieGenre[i]);
            tvShows.setRelease(movieRelease[i]);
            list.add(tvShows);
        }
        return listTv;
    }

    private void showSelectedTvShows(TvShows tvShows) {
        Intent detailActivity = new Intent(this.getActivity(), DetailActivityTv.class);
        detailActivity.putExtra(DetailActivityTv.EXTRA_TV_SHOW, tvShows);
        startActivity(detailActivity);
    }
}
