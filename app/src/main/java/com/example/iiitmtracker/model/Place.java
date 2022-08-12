package com.example.iiitmtracker.model;

public class Place {
    public String name;
    public String Longitude;
    public String Latitude;

    public Place(String name) {
        this.name = name;
    }

    public Place(String name, String latitude, String longitude) {
        this.name = name;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }
}
