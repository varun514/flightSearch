package com.ixigo.flightsearch.ui.showFlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Flights;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowFlightAdapter extends RecyclerView.Adapter<ShowFlightAdapter.ShowFlightViewAdapter> {
    Context context;
    private ArrayList<Flights> flights;

    public static class ShowFlightViewAdapter extends RecyclerView.ViewHolder{
        TextView origin;
        TextView destination;
        public ShowFlightViewAdapter(View v){
            super(v);
            origin = v.findViewById(R.id.origin);
            destination = v.findViewById(R.id.destination);
        }
    }

    public ShowFlightAdapter(Context context, ArrayList<Flights> flights){
        this.context = context;
        this.flights = flights;
    }



    @NonNull
    @Override
    public ShowFlightAdapter.ShowFlightViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight,parent,false);
        ShowFlightViewAdapter showFlightViewAdapter = new ShowFlightViewAdapter(v);
        return showFlightViewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFlightViewAdapter holder, int position) {
        holder.origin.setText(flights.get(position).getOriginCode());
        holder.destination.setText(flights.get(position).getDestinationCode());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}
