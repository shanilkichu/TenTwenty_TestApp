package com.myapp.tentwenty_movie.view.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.myapp.tentwenty_movie.R;
import com.myapp.tentwenty_movie.databinding.ActivityVideoviewBinding;
import com.myapp.tentwenty_movie.utils.YoutubeAPI;

import java.util.ArrayList;
import java.util.List;

public class Activity_VideoView extends YouTubeBaseActivity {

    ActivityVideoviewBinding binding;
    Activity activity=this;
    YouTubePlayer.OnInitializedListener mOninitializedListner;
    List<String>movieTrailers=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityVideoviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFunc();

    }

    private void initFunc() {
        movieTrailers = getIntent().getStringArrayListExtra("VideoID");
        System.out.println("MOVIES LIST  ----  " + movieTrailers);
        mOninitializedListner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);
                youTubePlayer.loadVideos(movieTrailers);
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {

                    }

                    @Override
                    public void onLoaded(String s) {

                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {
                        onBackPressed();
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        };

        binding.playerYoutube.initialize(YoutubeAPI.getYoutubeApiKey(), mOninitializedListner);

    }

}
