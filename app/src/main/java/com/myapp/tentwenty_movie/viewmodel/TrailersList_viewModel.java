package com.myapp.tentwenty_movie.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.tentwenty_movie.model.Trailers.MovieTrailers;
import com.myapp.tentwenty_movie.repository.TrailersRepository;

public class TrailersList_viewModel extends ViewModel {
    private final TrailersRepository repository;
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<MovieTrailers> TrailersMutableLiveData;

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

    public MutableLiveData<MovieTrailers> getMoviesListMutableLiveData() {
        if(TrailersMutableLiveData==null)
        {
            TrailersMutableLiveData=new MutableLiveData<>();
        }
        return TrailersMutableLiveData;
    }
    public TrailersList_viewModel() {
        repository=new TrailersRepository();
        errorREsponseMsg=repository.getErrorREsponseMsg();
        progressObservablle=repository.getProgressObservablle();
        TrailersMutableLiveData=repository.getTrailersMutableLiveData();
    }
    public void getTrailers(String strID)
    {
        repository.get_Trailers(strID);
    }
}
