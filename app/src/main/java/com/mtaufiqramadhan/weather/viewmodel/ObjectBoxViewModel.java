package com.mtaufiqramadhan.weather.viewmodel;

import com.mtaufiqramadhan.weather.repository.WeatherBoxModel;
import com.mtaufiqramadhan.weather.repository.WeatherBoxModel_;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;

public class ObjectBoxViewModel extends ViewModel {

    private ObjectBoxLiveData<WeatherBoxModel> weatherBoxLiveData;

    @Inject
    public ObjectBoxViewModel() {
        //Empty Construtor for Inject
    }

    public ObjectBoxLiveData<WeatherBoxModel> getWeatherBoxModel(Box<WeatherBoxModel> weatherBox) {
        if (weatherBoxLiveData == null) {
            weatherBoxLiveData = new ObjectBoxLiveData<>(
                    weatherBox.query()
                            .order(WeatherBoxModel_.id)
                            .build());
        }
        return weatherBoxLiveData;
    }

}
