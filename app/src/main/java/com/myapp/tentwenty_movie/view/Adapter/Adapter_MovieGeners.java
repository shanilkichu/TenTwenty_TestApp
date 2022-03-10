package com.myapp.tentwenty_movie.view.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.view.Activity.Activity_MovieDetails;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;
import java.util.Random;

public class Adapter_MovieGeners extends RecyclerView.Adapter<Adapter_MovieGeners.ViewHolder>{

    Activity activity;
    Context context;
    List<String> movieGeners;
    public Adapter_MovieGeners(List<String> movieGeners, Activity_MovieDetails activity_movieDetails, Context applicationContext) {
        this.movieGeners=movieGeners;
        this.activity=activity_movieDetails;
        this.context=applicationContext;
    }


    @Override
    public Adapter_MovieGeners.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_geners_item, parent, false);
        Adapter_MovieGeners.ViewHolder viewHolder = new Adapter_MovieGeners.ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(Adapter_MovieGeners.ViewHolder holder, int position) {
        System.out.println("SHANIL KICHU ---  "+movieGeners.size());
        holder.tvGenerName.setText(movieGeners.get(position));
        holder.cvGenere.setCardBackgroundColor(getRandomColorCode());
    }

    @Override
    public int getItemCount() {

        return movieGeners.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenerName;
        CardView cvGenere;
        public ViewHolder(View itemView) {
            super(itemView);
            tvGenerName=itemView.findViewById(R.id.tvGenerName);
            cvGenere=itemView.findViewById(R.id.cvGenere);
        }
    }
    public int getRandomColorCode(){

        Random random = new Random();
        return Color.argb(97, random.nextInt(195), random.nextInt(242),
                random.nextInt(1));

    }
}  