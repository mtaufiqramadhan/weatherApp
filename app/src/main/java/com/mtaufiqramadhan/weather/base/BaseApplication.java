package com.mtaufiqramadhan.weather.base;

import android.annotation.SuppressLint;
import android.app.Application;

import com.mtaufiqramadhan.weather.di.AppComponent;
import com.mtaufiqramadhan.weather.di.DaggerAppComponent;
import com.mtaufiqramadhan.weather.repository.MyObjectBox;
import com.mtaufiqramadhan.weather.repository.WeatherBoxModel;

import io.objectbox.Box;
import io.objectbox.BoxStore;

@SuppressLint("Registered")
public class BaseApplication extends Application {

    private AppComponent appComponent;
    private BoxStore boxStore;

    //Global Variable call weatherBox
    public static Box<WeatherBoxModel> weatherBox;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .build();
        boxStore = MyObjectBox.builder()
                .androidContext(this)
                .build();
        weatherBox = getBoxStore().boxFor(WeatherBoxModel.class);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }

}
