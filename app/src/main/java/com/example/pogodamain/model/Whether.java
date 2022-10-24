package com.example.pogodamain.model;

public class Whether {
    private double lat, lon;
    private String timezone;
    private WhetherItem current;

    public Whether(double lat, double lon, String timezone, WhetherItem current){
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.current = current;
    }

    public double getLat(){return  lat;}
    public void setLat(double lat) {this.lat = lat;}
    public double getLon(){return lon;}
    public void setLon(double lon) {this.lon = lon;}
    public String getTimezone(){return timezone;}
    public void setTimezone(String timeZone){this.timezone = timeZone;}
    public WhetherItem getCurrent() {return current;}
    public void setCurrent(WhetherItem current){this.current = current;}

}
