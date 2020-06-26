package com.ixigo.flightsearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Flights {
    private String originCode;
    private String destinationCode;
    private Long departureTime,arrivalTime;
    private ArrayList<Fares> fares;
    private String airlineCode;
    private String flyClass;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlyClass() {
        return flyClass;
    }

    public void setFlyClass(String flyClass) {
        this.flyClass = flyClass;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<Fares> getFares() {
        return fares;
    }

    public void setFares(ArrayList<Fares> fares) {
        this.fares = fares;
    }
}
