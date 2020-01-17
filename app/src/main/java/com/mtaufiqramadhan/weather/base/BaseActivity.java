package com.mtaufiqramadhan.weather.base;

import android.content.Intent;
import android.os.Bundle;

import com.mtaufiqramadhan.weather.R;
import com.mtaufiqramadhan.weather.repository.WeatherBoxModel;
import com.mtaufiqramadhan.weather.ui.DetailActivity;
import com.mtaufiqramadhan.weather.ui.MainActivity;
import com.mtaufiqramadhan.weather.viewmodel.ObjectBoxViewModel;
import com.mtaufiqramadhan.weather.viewmodel.RetrofitViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public ObjectBoxViewModel objectBoxViewModel;
    @Inject
    public RetrofitViewModel retrofitViewModel;
    @Inject
    public WeatherBoxModel weatherBoxModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((BaseApplication) getApplicationContext()).getAppComponent().inject(this);
        ((BaseApplication) getApplicationContext()).getBoxStore().boxFor(WeatherBoxModel.class);
        super.onCreate(savedInstanceState);
    }

    public abstract void setupFindViews();

    public abstract void setupListData();

    public abstract void setupListener();

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    public void goToDetailActivity() {
        startActivity(new Intent(this, DetailActivity.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

}
