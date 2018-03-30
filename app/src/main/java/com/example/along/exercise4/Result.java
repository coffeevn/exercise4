package com.example.along.exercise4;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;
@Parcel
public class Result{
    @SerializedName("vote_count")
    private long vote_count;
    @SerializedName("id")
    private long id;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private double vote_average;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("genre_ids")
    private List<Integer> genre_ids;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String release_date;

    public Result() {
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }
}
