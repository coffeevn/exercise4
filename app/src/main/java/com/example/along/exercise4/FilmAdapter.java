package com.example.along.exercise4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder>{
    ArrayList<Result> results;
    Context context;

    public FilmAdapter(ArrayList<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_film,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(results.get(position).getTitle());
        //https://image.tmdb.org/t/p/w500/
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+results.get(position).getPoster_path()).into(holder.poster);
        if (results.get(position).isVideo()){
            holder.posterWithIcon.setVisibility(View.VISIBLE);
        }
        holder.overview.setText(results.get(position).getOverview());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result selectedResult = results.get(position);
                Intent i = new Intent(v.getContext(),Detail.class);
                i.putExtra("selectedResult", Parcels.wrap(selectedResult));
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView poster;
        ImageView posterWithIcon;
        TextView overview;
        LinearLayout llItem;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            overview = itemView.findViewById(R.id.tvOverview);
            poster = itemView.findViewById(R.id.ivPoster);
            posterWithIcon = itemView.findViewById(R.id.ivPosterWithIcon);
            llItem = itemView.findViewById(R.id.llItem);
        }
    }
}
