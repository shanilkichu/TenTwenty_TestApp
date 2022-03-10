package com.myapp.tentwenty_movie.repository;

import androidx.lifecycle.MutableLiveData;

import com.myapp.tentwenty_movie.model.listAllMovies.ListAllMoviesModel;
import com.myapp.tentwenty_movie.network.APIInterface;
import com.myapp.tentwenty_movie.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAllMoviesRepository {
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<ListAllMoviesModel>movieslistMutableLiveData;

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
        if(movieslistMutableLiveData==null)
        {
            movieslistMutableLiveData=new MutableLiveData<>();
        }
        return movieslistMutableLiveData;
    }
    public void getUpcoming_Movies()
    {
        progressObservablle.setValue(true);
        APIInterface apiInterface= ApiClient.getRetrofit().create(APIInterface.class);
        Call<ListAllMoviesModel> call= apiInterface.getUpcoming_Movies();
        call.enqueue(new Callback<ListAllMoviesModel>() {
            @Override
            public void onResponse(Call<ListAllMoviesModel> call, Response<ListAllMoviesModel> response) {
                try {
                    progressObservablle.setValue(false);
                    if (response.isSuccessful() && response.body() != null) {
                        movieslistMutableLiveData.setValue(response.body());
                    } else {
                        errorREsponseMsg.setValue("Some error occurred");
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListAllMoviesModel> call, Throwable t) {
                t.printStackTrace();
                progressObservablle.setValue(false);
                errorREsponseMsg.setValue(t.getMessage());
            }
        });
    }
}