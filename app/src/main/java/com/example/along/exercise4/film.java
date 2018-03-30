package com.example.along.exercise4;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class film {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("results")
    private List<Result> results;
    @SerializedName("dates")
    private Date dates;

    public List<Result> getResults() {
        return results;
    }
}
