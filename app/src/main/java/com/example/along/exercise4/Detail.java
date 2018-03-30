package com.example.along.exercise4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Result selectedResult = (Result) Parcels.unwrap(getIntent().getParcelableExtra("selectedResult"));

        String title = selectedResult.getTitle();
        String imagelink = "https://image.tmdb.org/t/p/w500/"+selectedResult.getPoster_path();
        String decription = selectedResult.getOverview();
        String date = selectedResult.getRelease_date();
        float rating_fix = (float) Math.round(selectedResult.getVote_average()/2*10)/10;

        ImageView ivPlayer = findViewById(R.id.ivPlayer);
        Picasso.with(this).load(imagelink).into(ivPlayer);
        TextView tvTitleDetail = findViewById(R.id.tvTitleDetail);
        tvTitleDetail.setText(title);
        TextView tvDate = findViewById(R.id.tvDate);
        tvDate.setText("Release Date: "+date);
        TextView tvDescription = findViewById(R.id.tvDescription);
        tvDescription.setText(decription);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(rating_fix);

    }
}
