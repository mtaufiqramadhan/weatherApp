package com.mtaufiqramadhan.weather.di;

import com.google.gson.Gson;
import com.mtaufiqramadhan.weather.network.HttpMethods;
import com.mtaufiqramadhan.weather.repository.WeatherBoxModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson().newBuilder().create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    HttpMethods provideHttpMethods(Retrofit.Builder builder) {
        return builder.build().create(HttpMethods.class);
    }

    @Provides
    @Singleton
    WeatherBoxModel provideWeatherBoxModel() {
        return new WeatherBoxModel();
    }

}
