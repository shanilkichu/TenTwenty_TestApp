package com.myapp.tentwenty_movie.repository;

import androidx.lifecycle.MutableLiveData;

import com.myapp.tentwenty_movie.model.Trailers.MovieTrailers;
import com.myapp.tentwenty_movie.network.APIInterface;
import com.myapp.tentwenty_movie.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailersRepository {
    private MutableLiveData<Boolean> progressObservablle;
    private MutableLiveData<String>errorREsponseMsg;
    public MutableLiveData<MovieTrailers>TrailersMutableLiveData;

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

    public MutableLiveData<MovieTrailers> getTrailersMutableLiveData() {
        if(TrailersMutableLiveData==null)
        {
            TrailersMutableLiveData=new MutableLiveData<>();
        }
        return TrailersMutableLiveData;
    }
    public void get_Trailers(String strID)
    {
        progressObservablle.setValue(true);
        APIInterface apiInterface= ApiClient.getRetrofit().create(APIInterface.class);
        Call<MovieTrailers> call= apiInterface.getTrailers(strID);
        call.enqueue(new Callback<MovieTrailers>() {
            @Override
            public void onResponse(Call<MovieTrailers> call, Response<MovieTrailers> response) {
                try {
                    progressObservablle.setValue(false);
                    if (response.isSuccessful() && response.body() != null) {
                        TrailersMutableLiveData.setValue(response.body());
                    } else {
                        errorREsponseMsg.setValue("Some error occurred");
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MovieTrailers> call, Throwable t) {
                t.printStackTrace();
                progressObservablle.setValue(false);
                errorREsponseMsg.setValue(t.getMessage());
            }
        });
    }
}