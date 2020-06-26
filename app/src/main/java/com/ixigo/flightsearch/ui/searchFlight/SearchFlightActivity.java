package com.ixigo.flightsearch.ui.searchFlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.network.JsonApiHolder;
import com.ixigo.flightsearch.network.JsonApiService;

import org.w3c.dom.Text;

public class SearchFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonApiService jsonApiService = new JsonApiService();
           jsonApiService.start();
    }
}