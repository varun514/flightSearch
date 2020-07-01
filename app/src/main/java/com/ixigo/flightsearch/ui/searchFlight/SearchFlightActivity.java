package com.ixigo.flightsearch.ui.searchFlight;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.databinding.ActivityMainBinding;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.network.JsonApiService;
import com.ixigo.flightsearch.ui.showFlight.ShowFlightActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class SearchFlightActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SearchFlightViewModel searchFlightViewModel;
    private DataJson dataJson;
    HashMap<String,String> airports;
    ArrayList<String> mAirports = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //JsonApiService jsonApiService = new JsonApiService();
        //jsonApiService.start();
        searchFlightViewModel = new ViewModelProvider(this).get(SearchFlightViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchFlightViewModel.mDataJson.observe(this,it -> {
            airports = it.getAppendix().getAirports();
            for(String v : airports.values()){
                mAirports.add(v);
            }
            adapter.notifyDataSetChanged();
            Log.d("SEARCHFLIGHT",mAirports.size() + "");
        });

        searchFlightViewModel.fetchApiData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,mAirports);
        binding.origin.setAdapter(adapter);
        binding.Search.setOnClickListener(v -> {

            Intent i = new Intent(getBaseContext(), ShowFlightActivity.class);
            startActivity(i);
        });

    }
}