package com.myapp.tentwenty_movie.view.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.model.listAllMovies.Result;
import com.myapp.tentwenty_movie.utils.SharedHelper;
import com.myapp.tentwenty_movie.view.Activity.Activity_MovieDetails;

import java.io.File;
import java.util.List;

public class MoviewList_Adapter extends RecyclerView.Adapter<MoviewList_Adapter.ViewHolder>{

    List<Result> resultData;
    Activity activity;

    public MoviewList_Adapter(List<Result> resultData, Activity activity) {

        this.resultData=resultData;
        this.activity=activity;

    }


    @Override
    public MoviewList_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.moview_item, parent, false);
        MoviewList_Adapter.ViewHolder viewHolder = new MoviewList_Adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(MoviewList_Adapter.ViewHolder holder, int position) {
        holder.tvMovieName.setText(resultData.get(position).getTitle());
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedHelper.putKey2(activity,"id", resultData.get(position).getId());
                activity.startActivity(new Intent(activity, Activity_MovieDetails.class).
                        putExtra("overview",resultData.get(position).getOverview()).
                        putExtra("title",resultData.get(position).getTitle()).
                        putExtra("date",resultData.get(position).getReleaseDate()).
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }

    @Override
    public int getItemCount() {

        return resultData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        ImageView imgMoviePoster;
        CardView cvMovie;
        public ViewHolder(View itemView) {
            super(itemView);
            tvMovieName=itemView.findViewById(R.id.tvMoviewName);
            imgMoviePoster=itemView.findViewById(R.id.imgMovieposter);
            cvMovie=itemView.findViewById(R.id.cvMovie);
        }
    }
}  