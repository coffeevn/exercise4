package com.example.along.exercise4;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        final TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Now Playing
        try{
            run();
        } catch (Exception e){
            e.printStackTrace();
        }

        //Top Rate
        try{
            run1();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    void run() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        Request request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build()).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        film myFilm = gson.fromJson(myResponse,film.class);

                        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(layoutManager);
                        ArrayList<Result> arrayList = new ArrayList<>();
                        for (int i = 0; i<myFilm.getResults().size();i++){
                            arrayList.add(i,myFilm.getResults().get(i));
                        }
                        FilmAdapter filmAdapter = new FilmAdapter(arrayList,getApplicationContext());
                        recyclerView.setAdapter(filmAdapter);

                        //pull to refresh
                        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_nowplaying);
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                ArrayList<Result> arrayList1 = new ArrayList<>();
                                FilmAdapter filmAdapter1 = new FilmAdapter(arrayList1,getApplicationContext());
                                recyclerView.setAdapter(filmAdapter1);
                                swipeRefreshLayout.setRefreshing(false);
                                OkHttpClient client = new OkHttpClient();
                                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
                                Request request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build()).url(url).build();
                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        call.cancel();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        final String myResponseRefresh = response.body().string();
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Gson gson = new Gson();
                                                film myFilm = gson.fromJson(myResponseRefresh, film.class);

                                                final RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                                recyclerView.setHasFixedSize(true);
                                                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                                                recyclerView.setLayoutManager(layoutManager);
                                                ArrayList<Result> arrayList = new ArrayList<>();
                                                for (int i = 0; i < myFilm.getResults().size(); i++) {
                                                    arrayList.add(i, myFilm.getResults().get(i));
                                                }
                                                FilmAdapter filmAdapter = new FilmAdapter(arrayList, getApplicationContext());
                                                recyclerView.setAdapter(filmAdapter);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    void run1(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        Request request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build()).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        film myFilm = gson.fromJson(myResponse,film.class);

                        final RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(layoutManager);
                        ArrayList<Result> arrayList = new ArrayList<>();
                        for (int i = 0; i<myFilm.getResults().size();i++){
                            arrayList.add(i,myFilm.getResults().get(i));
                        }
                        FilmAdapter filmAdapter = new FilmAdapter(arrayList,getApplicationContext());
                        recyclerView.setAdapter(filmAdapter);

                        //pull to refresh
                        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_toprate);
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                ArrayList<Result> arrayList1 = new ArrayList<>();
                                FilmAdapter filmAdapter1 = new FilmAdapter(arrayList1,getApplicationContext());
                                recyclerView.setAdapter(filmAdapter1);
                                swipeRefreshLayout.setRefreshing(false);
                                OkHttpClient client = new OkHttpClient();
                                String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
                                Request request = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build()).url(url).build();
                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        call.cancel();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        final String myResponseRefresh = response.body().string();
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Gson gson = new Gson();
                                                film myFilm = gson.fromJson(myResponseRefresh, film.class);

                                                final RecyclerView recyclerView = findViewById(R.id.recyclerView2);
                                                recyclerView.setHasFixedSize(true);
                                                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                                                recyclerView.setLayoutManager(layoutManager);
                                                ArrayList<Result> arrayList = new ArrayList<>();
                                                for (int i = 0; i < myFilm.getResults().size(); i++) {
                                                    arrayList.add(i, myFilm.getResults().get(i));
                                                }
                                                FilmAdapter filmAdapter = new FilmAdapter(arrayList, getApplicationContext());
                                                recyclerView.setAdapter(filmAdapter);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

}