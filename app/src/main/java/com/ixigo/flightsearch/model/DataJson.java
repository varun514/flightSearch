package com.ixigo.flightsearch.model;

import java.util.ArrayList;

public class DataJson {
    public Appendix getAppendix() {
        return appendix;
    }

    public void setAppendix(Appendix appendix) {
        this.appendix = appendix;
    }

    public ArrayList<Flights> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flights> flights) {
        this.flights = flights;
    }

    private Appendix appendix;
    private ArrayList<Flights> flights;
}
