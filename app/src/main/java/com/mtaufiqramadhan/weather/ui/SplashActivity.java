package com.mtaufiqramadhan.weather.ui;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.mtaufiqramadhan.weather.R;
import com.mtaufiqramadhan.weather.base.BaseActivity;

import static com.mtaufiqramadhan.weather.base.BaseApplication.weatherBox;

public class SplashActivity extends BaseActivity {

    private TextView tv_versionCode;

    private boolean dataEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupFindViews();
    }

    @Override
    public void setupFindViews() {
        tv_versionCode = findViewById(R.id.tv_versionCode);

        setupListData();
        setupListener();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setupListData() {
        tv_versionCode.setText("Versi  " + getCurrentVersionName());

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
                cekDatabase();
            }

            @Override
            public void onFinish() {
                if (dataEmpty) {
                    goToMainActivity();
                } else {
                    goToDetailActivity();
                }
            }
        }.start();
    }

    @Override
    public void setupListener() {

    }

    private void cekDatabase() {
        objectBoxViewModel.getWeatherBoxModel(weatherBox).observe(this, weathers -> {
            try {
                if (weathers.get(0).getLog() == 1) {
                    dataEmpty = !dataEmpty;
                }
            } catch (IndexOutOfBoundsException ignored) {
                // if size weathers == 0
            }
        });
    }

    private String getCurrentVersionName(){
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        }catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "";
    }

}
