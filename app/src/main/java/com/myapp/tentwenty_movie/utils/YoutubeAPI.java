package com.myapp.tentwenty_movie.utils;

public class YoutubeAPI {

    public YoutubeAPI(){

    }

    public static String YOUTUBE_API_KEY="AIzaSyDQE4O6F_uMOGO7KJSwyO9WTtXw5WpKPH4";

    public static String getYoutubeApiKey() {
        return YOUTUBE_API_KEY;
    }

    public static void setYoutubeApiKey(String youtubeApiKey) {
        YOUTUBE_API_KEY = youtubeApiKey;
    }
}
