package com.ixigo.flightsearch.ui.showFlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Fares;
import com.ixigo.flightsearch.model.Flights;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowFlightAdapter extends RecyclerView.Adapter<ShowFlightAdapter.ShowFlightViewAdapter> {
    Context context;
    private ArrayList<Flights> flights;

    public static class ShowFlightViewAdapter extends RecyclerView.ViewHolder{
        TextView originToDestination;
        TextView time;
        public ShowFlightViewAdapter(View v){
            super(v);
            originToDestination = v.findViewById(R.id.originToDestination);
            time = v.findViewById(R.id.time);
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
        String originToDestination = flights.get(position).getOriginCode() + " -> " + flights.get(position).getDestinationCode();
        holder.originToDestination.setText(originToDestination);
        Date dateDeparture = new Date(flights.get(position).getDepartureTime());
        Date dateArrival = new Date(flights.get(position).getArrivalTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeDeparture = simpleDateFormat.format(dateDeparture);
        String timeArrival = simpleTimeFormat.format(dateArrival);
        holder.time.setText(timeDeparture + "-" + timeArrival);

    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}
