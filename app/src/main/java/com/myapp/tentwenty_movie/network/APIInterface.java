package com.myapp.tentwenty_movie.network;


import com.myapp.tentwenty_movie.model.MovieDetails.MovieDetails;
import com.myapp.tentwenty_movie.model.Trailers.MovieTrailers;
import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET(APIConstants.LIST_UPCOMING_MOVIES)
    Call<ListAllMoviesModel>getUpcoming_Movies();

    @GET(APIConstants.LIST_MOVIE_DETAILS)
    Call<MovieDetails>getMovieDetails(@Path("movie-id") String id);

    @GET(APIConstants.LIST_MOVIE_TRAILERS)
    Call<MovieTrailers> getTrailers(@Path("movie-id") String strID);
}
