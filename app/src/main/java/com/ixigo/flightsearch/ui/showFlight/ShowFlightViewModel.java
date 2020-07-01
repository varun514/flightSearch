package com.ixigo.flightsearch.ui.showFlight;

import android.util.Log;

import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.network.JsonApiHolder;
import com.ixigo.flightsearch.network.JsonApiService;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowFlightViewModel extends ViewModel {
    public MutableLiveData<DataJson> mDataJson = new MutableLiveData<DataJson>();
    private DataJson dataJson;
    public void fetchApiData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo9502422.mockable.io/")
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
    }

}
