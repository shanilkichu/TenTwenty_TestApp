package com.myapp.tentwenty_movie.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.tentwenty_movie.model.MovieDetails.MovieDetails;
import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;
import com.myapp.tentwenty_movie.repository.ListAllMoviesRepository;
import com.myapp.tentwenty_movie.repository.MovieDetailsRepository;

public class MovieDetails_Viewmodel extends ViewModel {
    private final MovieDetailsRepository repository;
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<MovieDetails>moviesDetailsMutableLiveData;

    public MutableLiveData<Boolean> getProgressObservablle()
    {
        if(progressObservablle==null)
        {
            progressObservablle=new MutableLiveData<>();
        }

        return progressObservablle;
    }

    public MutableLiveData<String>getErrorREsponseMsg()
    {
        if(errorREsponseMsg==null)
        {
            errorREsponseMsg=new MutableLiveData<>();
        }
        return errorREsponseMsg;
    }

    public MutableLiveData<MovieDetails> getMoviesListMutableLiveData() {
        if(moviesDetailsMutableLiveData==null)
        {
            moviesDetailsMutableLiveData=new MutableLiveData<>();
        }
        return moviesDetailsMutableLiveData;
    }
    public MovieDetails_Viewmodel() {
        repository=new MovieDetailsRepository();
        errorREsponseMsg=repository.getErrorREsponseMsg();
        progressObservablle=repository.getProgressObservablle();
        moviesDetailsMutableLiveData=repository.getmovieDetailsMutableLiveData();
    }
    public void getMoviedetails(String strID)
    {
        repository.getMovie_Details(strID);
    }
}
