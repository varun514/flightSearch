
package com.ixigo.flightsearch.ui.showFlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ixigo.flightsearch.databinding.ActivityShowFlightsBinding;
import com.ixigo.flightsearch.model.Appendix;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ShowFlightActivity extends AppCompatActivity {
    private ActivityShowFlightsBinding binding;
    private ShowFlightViewModel showFlightVM;
    public DataJson mDataJson;
    public ArrayList<Flights> flights = new ArrayList<Flights>();
    public Appendix appendix = new Appendix();
    ShowFlightAdapter showFlightAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String origin = getIntent().getStringExtra("origin");
        String destination = getIntent().getStringExtra("destination");

        super.onCreate(savedInstanceState);
        showFlightVM = new ViewModelProvider(this).get(ShowFlightViewModel.class);
        binding = ActivityShowFlightsBinding.inflate(getLayoutInflater());
        binding.from.setText(origin);
        binding.to.setText(destination);
        setContentView(binding.getRoot());
        recyclerView = binding.recyclerView;
        showFlightAdapter = new ShowFlightAdapter(this,flights,appendix);
        recyclerView.setAdapter(showFlightAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showFlightVM.mDataJson.observe(this, it -> {
            for(Flights v: it.getFlights()){
                if(v.getOriginCode().matches(origin) && v.getDestinationCode().matches(destination))
                    flights.add(v);
            }
            appendix.setAirlines(it.getAppendix().getAirlines());
            appendix.setAirports(it.getAppendix().getAirports());
            appendix.setProviders(it.getAppendix().getProviders());
            Log.d("SHOW",origin);
            Log.d("SHOW",destination);
            Log.d("SHOW",flights.size() + "");
            showFlightAdapter.setAdapterData(flights,appendix);
        });
        binding.departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flights!=null){
                    Collections.sort(flights,comparatorTime());
                    showFlightAdapter.setAdapterData(flights,appendix);
                }
            }
        });
        showFlightVM.fetchApiData();
        binding.arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flights!=null){
                    Collections.sort(flights,comparatorTime());
                    showFlightAdapter.setAdapterData(flights,appendix);
                }
            }
        });
    }

    private static Comparator<Flights> comparatorTime(){
        Comparator comp = new Comparator<Flights>() {
            public int compare(Flights o1, Flights o2) {
                return (int) (o1.getArrivalTime() - o2.getArrivalTime());
            }
        };
        return comp;
    }
}