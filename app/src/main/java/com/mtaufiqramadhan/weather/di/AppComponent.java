package com.mtaufiqramadhan.weather.di;

import com.mtaufiqramadhan.weather.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(BaseActivity baseActivity);

}
