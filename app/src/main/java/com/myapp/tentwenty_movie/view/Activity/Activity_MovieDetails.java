package com.myapp.tentwenty_movie.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.databinding.ActivityMoviedetailsBinding;
import com.myapp.tentwenty_movie.model.MovieDetails.MovieDetails;
import com.myapp.tentwenty_movie.model.Trailers.MovieTrailers;
import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;
import com.myapp.tentwenty_movie.utils.CustomDialog;
import com.myapp.tentwenty_movie.utils.SharedHelper;
import com.myapp.tentwenty_movie.utils.Util;
import com.myapp.tentwenty_movie.view.Adapter.Adapter_MovieGeners;
import com.myapp.tentwenty_movie.view.Adapter.MoviewList_Adapter;
import com.myapp.tentwenty_movie.viewmodel.ListAllMovies_viewModel;
import com.myapp.tentwenty_movie.viewmodel.MovieDetails_Viewmodel;
import com.myapp.tentwenty_movie.viewmodel.TrailersList_viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_MovieDetails extends AppCompatActivity {

    ActivityMoviedetailsBinding binding;
    String strreleaseDate;
    String strTitle;
    String strOverview;
    String strID;
    Activity activity=this;
    CustomDialog customDialog;
    List<String> MovieGeners=new ArrayList<>();
    List<String> TrailerLinks=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMoviedetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBundle();

    }

    private void initBundle() {
        customDialog = new CustomDialog(activity);
        strID = SharedHelper.getKey(activity, "id");
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.clGetTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity, Activity_SeatBooking.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });
        binding.clWatchTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrailerLinkVideos(strID);
            }
        });
        movieDetails();
    }

    private void movieDetails() {

        customDialog = new CustomDialog(activity);
        customDialog.show();
        MovieDetails_Viewmodel viewModel = new ViewModelProvider(this).get(MovieDetails_Viewmodel.class);
        if (Util.isNetworkAvailable(activity)) {
            viewModel.getMoviedetails(strID);
        } else {
            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }


        viewModel.getMoviesListMutableLiveData().observe(this, new Observer<MovieDetails>() {
            @Override
            public void onChanged(MovieDetails movieDetails) {
                customDialog.dismiss();
                setDataView(movieDetails);

            }
        });
        viewModel.getProgressObservablle().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    customDialog.show();
                } else {
                    customDialog.dismiss();
                }
            }
        });
        viewModel.getErrorREsponseMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void TrailerLinkVideos(String ID) {

        customDialog = new CustomDialog(activity);
        customDialog.show();
        TrailersList_viewModel viewModel = new ViewModelProvider(this).get(TrailersList_viewModel.class);
        if (Util.isNetworkAvailable(activity)) {
            viewModel.getTrailers(ID);
        } else {
            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
        viewModel.getMoviesListMutableLiveData().observe(this, new Observer<MovieTrailers>() {
            @Override
            public void onChanged(MovieTrailers movieTrailers) {
                customDialog.dismiss();

                for (int i = 0; i < movieTrailers.getResults().size(); i++) {
                    if (movieTrailers.getResults().get(i).getType().equals("Trailer")) {
                        TrailerLinks.add(movieTrailers.getResults().get(i).getKey());
                    }
                }

                startActivity(new Intent(activity, Activity_VideoView.class)
                        .putStringArrayListExtra("VideoID", (ArrayList<String>) TrailerLinks)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });
        viewModel.getProgressObservablle().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    customDialog.show();
                } else {
                    customDialog.dismiss();
                }
            }
        });
        viewModel.getErrorREsponseMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setDataView(MovieDetails movieDetails) {

        strreleaseDate = movieDetails.getReleaseDate();
        strOverview = movieDetails.getOverview();
        strTitle = movieDetails.getTitle();
        //date conversion
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date newDate;
            newDate = spf.parse(strreleaseDate);
            spf = new SimpleDateFormat("MMM dd ,yyyy");
            strreleaseDate = spf.format(newDate);
            binding.txtReleaseDate.setText("In theaters " + strreleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.txtMoviename.setText(strTitle);
        binding.txtOverviewdata.setText(strOverview);
        MovieGeners.clear();
        if (movieDetails.getGenres().size() > 0) {
            for (int i = 0; i < movieDetails.getGenres().size(); i++) {
                MovieGeners.add(movieDetails.getGenres().get(i).getName());
            }
            Adapter_MovieGeners adapter = new Adapter_MovieGeners(MovieGeners,
                    Activity_MovieDetails.this,
                    getApplicationContext());
            binding.recyGeners.setHasFixedSize(true);
            binding.recyGeners.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
            binding.recyGeners.setAdapter(adapter);
        }

    }
}
