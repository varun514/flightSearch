package com.ixigo.flightsearch.model;

import java.util.HashMap;
import java.util.List;

public class Appendix {
    private HashMap<String, String> airlines, airports, providers;

    public HashMap<String, String> getAirlines() {
        return airlines;
    }

    public void setAirlines(HashMap<String, String> airlines) {
        this.airlines = airlines;
    }

    public HashMap<String, String> getAirports() {
        return airports;
    }

    public void setAirports(HashMap<String, String> airports) {
        this.airports = airports;
    }

    public HashMap<String, String> getProviders() {
        return providers;
    }

    public void setProviders(HashMap<String, String> providers) {
        this.providers = providers;
    }
}
