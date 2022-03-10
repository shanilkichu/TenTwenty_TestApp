package com.myapp.tentwenty_movie.network;



public interface APIConstants {
    String BASE_URL="https://api.themoviedb.org/3/movie/";
    String LIST_UPCOMING_MOVIES="upcoming?api_key=8fce7bd0b7fc46ad12ff9c9e48451c3b";
    String LIST_MOVIE_DETAILS="{movie-id}?api_key=8fce7bd0b7fc46ad12ff9c9e48451c3b";
    String LIST_MOVIE_TRAILERS="{movie-id}/videos?api_key=8fce7bd0b7fc46ad12ff9c9e48451c3b";

}
