package com.ixigo.flightsearch.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.TextView;

import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;

import java.util.Observable;

import javax.xml.transform.Templates;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonApiService {

    DataJson dataJson;
    Context context;
    MutableLiveData<DataJson> mDataJson = new MutableLiveData<DataJson>();

    public MutableLiveData<DataJson> start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo2961096.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiHolder jsonApiHolder = retrofit.create(JsonApiHolder.class);
        Call<DataJson> call = jsonApiHolder.getData();
        call.enqueue(new Callback<DataJson>() {
            @Override
            public void onResponse(Call<DataJson> call, Response<DataJson> response) {
                if (!response.isSuccessful()) {
                    Log.d("TAG", "response inSuccessful");
                }
                else {
                    DataJson json = response.body();
                    Log.d("TAG", "response isSuccessful");
                    mDataJson.postValue(json);
                }
            }

            @Override
            public void onFailure(Call<DataJson> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
        return mDataJson;
    }
}
