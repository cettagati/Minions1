package com.teknokrat.mobile2019.ti17a17313026.minions;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class DetailActivityTv extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TvShows tvShows = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        setContentView(R.layout.activity_detail_tv);

        TextView tvTitle = findViewById(R.id.svshow_detail);
        ImageView imgPhoto = findViewById(R.id.svdetail_photo);
        TextView tvDescription = findViewById(R.id.svshow_description);
        TextView tvGenre = findViewById(R.id.svshow_genre);
        TextView tvRelease = findViewById(R.id.svshow_release);

        tvTitle.setText(Objects.requireNonNull(tvShows).getName());
        imgPhoto.setImageResource(tvShows.getPhoto());
        tvDescription.setText(tvShows.getDescription());
        tvGenre.setText(tvShows.getGenre());
        tvRelease.setText(tvShows.getRelease());
    }
}
