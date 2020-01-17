package com.mtaufiqramadhan.weather.repository;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class WeatherBoxModel {

    @Id
    long id;

    private String name;
    private String zip;
    private int log;

    public WeatherBoxModel() {
    }

    public WeatherBoxModel(String name, String zip, int log) {
        this.name = name;
        this.zip = zip;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }

}
