
package com.ixigo.flightsearch.ui.showFlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.databinding.ActivityMainBinding;
import com.ixigo.flightsearch.databinding.ActivityShowFlightsBinding;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;

import java.util.ArrayList;
import java.util.List;

public class ShowFlightActivity extends AppCompatActivity {
    private ActivityShowFlightsBinding binding;
    private ShowFlightViewModel showFlightVM;
    public DataJson mDataJson;
    public ArrayList<Flights> flights = new ArrayList<Flights>();
    ShowFlightAdapter showFlightAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFlightVM = new ViewModelProvider(this).get(ShowFlightViewModel.class);
        binding = ActivityShowFlightsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.recyclerView;
        showFlightAdapter = new ShowFlightAdapter(this,flights);
        //recyclerView.setAdapter(showFlightAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showFlightVM.mDataJson.observe(this, it -> {
            for(Flights v: it.getFlights()){
                flights.add(v);
            }
            Log.d("SHOW",flights.size() + "");
            showFlightAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(showFlightAdapter);
        });
        showFlightVM.fetchApiData();
    }
}