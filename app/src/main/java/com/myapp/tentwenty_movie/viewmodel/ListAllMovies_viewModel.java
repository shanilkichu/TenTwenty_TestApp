package com.myapp.tentwenty_movie.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;
import com.myapp.tentwenty_movie.repository.ListAllMoviesRepository;

public class ListAllMovies_viewModel extends ViewModel {
    private final ListAllMoviesRepository repository;
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<ListAllMoviesModel>moviesListMutableLiveData;

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

    public MutableLiveData<ListAllMoviesModel> getMoviesListMutableLiveData() {
        if(moviesListMutableLiveData==null)
        {
            moviesListMutableLiveData=new MutableLiveData<>();
        }
        return moviesListMutableLiveData;
    }
    public ListAllMovies_viewModel() {
        repository=new ListAllMoviesRepository();
        errorREsponseMsg=repository.getErrorREsponseMsg();
        progressObservablle=repository.getProgressObservablle();
        moviesListMutableLiveData=repository.getMoviesListMutableLiveData();
    }
    public void getList()
    {
        repository.getUpcoming_Movies();
    }
}
