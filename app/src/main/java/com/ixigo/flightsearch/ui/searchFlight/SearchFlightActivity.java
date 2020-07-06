package com.ixigo.flightsearch.ui.searchFlight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.ixigo.flightsearch.databinding.ActivityMainBinding;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.ui.showFlight.ShowFlightActivity;

import java.util.ArrayList;
import java.util.HashMap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class SearchFlightActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SearchFlightViewModel searchFlightViewModel;
    private DataJson dataJson;
    HashMap<String,String> airports;
    ArrayList<String> mAirports = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchFlightViewModel = new ViewModelProvider(this).get(SearchFlightViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchFlightViewModel.mDataJson.observe(this,it -> {
            airports = it.getAppendix().getAirports();
            for(String v : airports.keySet()){
                mAirports.add(v+":"+airports.get(v));
            }
            //
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mAirports);
            binding.origin.setAdapter(arrayAdapter);
            binding.destination.setAdapter(arrayAdapter);
            binding.origin.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(mAirports.size()>0){
                        binding.origin.showDropDown();
                    }
                    return false;
                }
            });
            binding.destination.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(mAirports.size()>0){
                        binding.destination.showDropDown();
                    }
                    return false;
                }
            });
            Log.d("SEARCHFLIGHT",mAirports.size() + "");
        });
        searchFlightViewModel.fetchApiData();
        binding.Search.setOnClickListener(v -> {
            Intent i = new Intent(getBaseContext(), ShowFlightActivity.class);
            i.putExtra("origin",binding.origin.getText().toString().subSequence(0,3));
            i.putExtra("destination",binding.destination.getText().toString().subSequence(0,3));
            startActivity(i);
        });
    }

}