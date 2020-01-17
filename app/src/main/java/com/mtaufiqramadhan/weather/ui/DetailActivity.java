package com.mtaufiqramadhan.weather.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtaufiqramadhan.weather.R;
import com.mtaufiqramadhan.weather.base.BaseActivity;

import java.text.DecimalFormat;
import java.util.Calendar;

import static com.mtaufiqramadhan.weather.base.BaseApplication.weatherBox;

public class DetailActivity extends BaseActivity {

    private TextView tv_helloName, tv_weatherMain, tv_weatherDesc, tv_celcius;
    private ImageView img_weather;
    private Button btn_try;

    private int timeOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupFindViews();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        retrofitViewModel.getStatus().observe(this, s -> {
            if (s != null) {
                btn_try.setVisibility(View.VISIBLE);
                tv_weatherDesc.setText("Sorry, you input invalid City");
            }
        });
    }

    @Override
    public void setupFindViews() {
        tv_helloName = findViewById(R.id.tv_helloName);
        tv_weatherMain = findViewById(R.id.tv_weatherMain);
        tv_weatherDesc = findViewById(R.id.tv_weatherDesc);
        tv_celcius = findViewById(R.id.tv_celcius);
        img_weather = findViewById(R.id.img_weather);
        btn_try = findViewById(R.id.btn_try);

        timeOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        setupListData();
        setupListener();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setupListData() {
        objectBoxViewModel.getWeatherBoxModel(weatherBox).observe(this, weathers -> {
            if (timeOfDay < 12) {
                tv_helloName.setText("Good Morning, \n" + weathers.get(0).getName() + ".");
            } else if (timeOfDay < 16) {
                tv_helloName.setText("Good Afternoon, \n" + weathers.get(0).getName() + ".");
            } else if (timeOfDay < 21) {
                tv_helloName.setText("Good Evening, \n" + weathers.get(0).getName() + ".");
            } else if (timeOfDay < 24) {
                tv_helloName.setText("Good Night, \n" + weathers.get(0).getName() + ".");
            }

            retrofitViewModel.getWeather(this, weathers.get(0).getZip()).observe(this, weathers1 -> {
                tv_weatherMain.setText(weathers1.get(0).getMain());
                tv_weatherDesc.setText(weathers1.get(0).getDescription());
                getWeatherIcon(weathers1.get(0).getIcon());
            });

            retrofitViewModel.getMain(this, weathers.get(0).getZip()).observe(this, weathers2 ->
                    tv_celcius.setText(new DecimalFormat("#").format(weathers2.getTemp() - 273.15) + "°")
            );
        });
    }

    @Override
    public void setupListener() {
        btn_try.setOnClickListener(view -> goToMainActivity());
    }

    private void getWeatherIcon(String icon) {
        switch (icon) {
            default:
                img_weather.setImageResource(R.drawable.icon_clear_sky);
                break;
            case "02d":
                img_weather.setImageResource(R.drawable.icon_few_clouds);
                break;
            case "03d":
                img_weather.setImageResource(R.drawable.icon_scattered_clouds);
                break;
            case "04d":
                img_weather.setImageResource(R.drawable.icon_broken_clouds);
                break;
            case "09d":
                img_weather.setImageResource(R.drawable.icon_shower_rain);
                break;
            case "10d":
                img_weather.setImageResource(R.drawable.icon_rain);
                break;
            case "11d":
                img_weather.setImageResource(R.drawable.icon_thunderstorm);
                break;
            case "13d":
                img_weather.setImageResource(R.drawable.icon_snow);
                break;
            case "50d":
                img_weather.setImageResource(R.drawable.icon_mist);
                break;
        }
    }

}
