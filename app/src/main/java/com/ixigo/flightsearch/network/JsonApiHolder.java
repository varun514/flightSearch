package com.ixigo.flightsearch.network;

import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApiHolder {

    @GET("flights")
    Call<DataJson> getData();
}
