package com.mtaufiqramadhan.weather.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import com.mtaufiqramadhan.weather.R;
import com.mtaufiqramadhan.weather.model.Main;
import com.mtaufiqramadhan.weather.model.Weather;
import com.mtaufiqramadhan.weather.model.WeatherData;
import com.mtaufiqramadhan.weather.network.HttpMethods;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitViewModel extends ViewModel {

    private MutableLiveData<List<Weather>> weatherResultData = new MutableLiveData<>();
    private MutableLiveData<Main> mainResultData = new MutableLiveData<>();
    private MutableLiveData<String> status = new MutableLiveData<>();

    private HttpMethods httpMethods;

    @Inject
    public RetrofitViewModel(HttpMethods httpMethods) {
        this.httpMethods = httpMethods;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<Weather>> getWeather(final Context mContext, final String country) {
        httpMethods.getWeatherData(country + ",ID", mContext.getString(R.string.API_KEY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Disposable
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        weatherResultData.setValue(weatherData.getWeather());
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //onComplete
                    }
                });
        return weatherResultData;
    }

    @SuppressLint("CheckResult")
    public LiveData<Main> getMain(final Context mContext, String country) {
        httpMethods.getWeatherData(country + ",ID", mContext.getString(R.string.API_KEY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Disposable
                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        mainResultData.setValue(weatherData.getMain());
                    }

                    @Override
                    public void onError(Throwable e) {
                        status.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //onComplete
                    }
                });
        return mainResultData;
    }

    public LiveData<String> getStatus() {
        return status;
    }

}
