package com.ixigo.flightsearch.ui.showFlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ixigo.flightsearch.R;
import com.ixigo.flightsearch.model.Appendix;
import com.ixigo.flightsearch.model.DataJson;
import com.ixigo.flightsearch.model.Fares;
import com.ixigo.flightsearch.model.Flights;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowFlightAdapter extends RecyclerView.Adapter<ShowFlightAdapter.ShowFlightViewAdapter> {
    Context context;
    private ArrayList<Flights> flights;
    private Appendix appendix;

    public static class ShowFlightViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView airlines;
        TextView time;
        TextView price;
        public ShowFlightViewAdapter(View v) {
            super(v);
            airlines = v.findViewById(R.id.Airlines);
            time = v.findViewById(R.id.time);
            price = v.findViewById(R.id.price);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public ShowFlightAdapter(Context context, ArrayList<Flights> flights, Appendix appendix) {
        this.context = context;
        this.flights = flights;
        this.appendix = appendix;
    }


    @NonNull
    @Override
    public ShowFlightAdapter.ShowFlightViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight2, parent, false);
        ShowFlightViewAdapter showFlightViewAdapter = new ShowFlightViewAdapter(v);
        return showFlightViewAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFlightViewAdapter holder, int position) {
        String airlines = appendix.getAirlines().get(flights.get(position).getAirlineCode());
        holder.airlines.setText(airlines);
        Date dateDeparture = new Date(flights.get(position).getDepartureTime());
        Date dateArrival = new Date(flights.get(position).getArrivalTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        String timeDeparture = simpleDateFormat.format(dateDeparture);
        String timeArrival = simpleTimeFormat.format(dateArrival);
        holder.time.setText(timeDeparture + "-" + timeArrival);
        String provider = appendix.getProviders().get(""+flights.get(position).getFares().get(0).getProviderId());
        holder.price.setText( provider+ "=" + flights.get(position).getFares().get(0).getFare());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public void setAdapterData(ArrayList<Flights> flights, Appendix appendix) {
        this.flights = flights;
        this.appendix = appendix;
    }

}
