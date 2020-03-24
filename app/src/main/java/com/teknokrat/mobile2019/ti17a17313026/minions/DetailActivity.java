package com.teknokrat.mobile2019.ti17a17313026.minions;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        setContentView(R.layout.activity_detail);

        TextView tvTitle = findViewById(R.id.tv_detail);
        ImageView imgPhoto = findViewById(R.id.detail_photo);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvGenre = findViewById(R.id.tv_genre);
        TextView tvRelease = findViewById(R.id.tv_release);

        tvTitle.setText(Objects.requireNonNull(movie).getName());
        imgPhoto.setImageResource(movie.getPhoto());
        tvDescription.setText(movie.getDescription());
        tvGenre.setText(movie.getGenre());
        tvRelease.setText(movie.getRelease());
    }
}
