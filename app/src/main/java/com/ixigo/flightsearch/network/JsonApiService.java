package com.ixigo.flightsearch.network;

import android.util.Log;
import android.widget.TextView;

import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonApiService {
    public void start(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo9502422.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiHolder jsonApiHolder = retrofit.create(JsonApiHolder.class);
        Call<DataJson> call = jsonApiHolder.getData();
        call.enqueue(new Callback<DataJson>() {
            @Override
            public void onResponse(Call<DataJson> call, Response<DataJson> response) {
                if(!response.isSuccessful()){
                    Log.d("TAG","response inSuccessful");
                }
                else{
                    DataJson dataJson = response.body();
                    for(Flights flight: dataJson.getFlights()){
                        Log.d("TAG",flight.getDestinationCode());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataJson> call, Throwable t) {
                Log.d("TAG",t.getMessage());
            }
        });
    }
}
