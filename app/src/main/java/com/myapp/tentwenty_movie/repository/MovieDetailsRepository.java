package com.myapp.tentwenty_movie.repository;

import androidx.lifecycle.MutableLiveData;

import com.myapp.tentwenty_movie.model.MovieDetails.MovieDetails;
import com.myapp.tentwenty_movie.network.APIInterface;
import com.myapp.tentwenty_movie.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<MovieDetails>movieDetailsMutableLiveData;

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

    public MutableLiveData<MovieDetails> getmovieDetailsMutableLiveData() {
        if(movieDetailsMutableLiveData==null)
        {
            movieDetailsMutableLiveData=new MutableLiveData<>();
        }
        return movieDetailsMutableLiveData;
    }
    public void getMovie_Details(String strID)
    {
        progressObservablle.setValue(true);
        APIInterface apiInterface= ApiClient.getRetrofit().create(APIInterface.class);
        Call<MovieDetails> call= apiInterface.getMovieDetails(strID);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                try {
                    progressObservablle.setValue(false);
                    if (response.isSuccessful() && response.body() != null) {
                        movieDetailsMutableLiveData.setValue(response.body());
                    } else {
                        errorREsponseMsg.setValue("Some error occurred");
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                t.printStackTrace();
                progressObservablle.setValue(false);
                errorREsponseMsg.setValue(t.getMessage());
            }
        });
    }
}