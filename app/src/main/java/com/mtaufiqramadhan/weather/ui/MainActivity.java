package com.mtaufiqramadhan.weather.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.Toast;

import com.mtaufiqramadhan.weather.R;
import com.mtaufiqramadhan.weather.base.BaseActivity;
import com.mtaufiqramadhan.weather.repository.WeatherBoxModel;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

import static com.mtaufiqramadhan.weather.base.BaseApplication.weatherBox;

public class MainActivity extends BaseActivity {

    private EditText et_name, et_zip;
    private CircularProgressButton btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFindViews();
    }

    @Override
    public void setupFindViews() {
        et_name = findViewById(R.id.et_name);
        et_zip = findViewById(R.id.et_zip);
        btn_save = findViewById(R.id.btn_save);

        setupListData();
        setupListener();
    }

    @Override
    public void setupListData() {

    }

    @Override
    public void setupListener() {
        btn_save.setOnClickListener(view -> formValidation());
    }

    private void formValidation() {
        String name = et_name.getText().toString().trim();
        String zip = et_zip.getText().toString().trim();
        if (name.equals("") && zip.equals("")) {
            Toast.makeText(this, "Oops, All of your field still empty !", Toast.LENGTH_LONG).show();
        } else if (name.equals("")) {
            Toast.makeText(this, "Oops, Your name still empty !", Toast.LENGTH_LONG).show();
        } else if (zip.equals("")) {
            Toast.makeText(this, "Oops, You forgot add Country !", Toast.LENGTH_LONG).show();
        } else {
            weatherBox.removeAll();

            weatherBoxModel = new WeatherBoxModel(name, zip, 1);
            weatherBox.put(weatherBoxModel);
            loadFormValidation();
        }
    }

    private void loadFormValidation() {
        btn_save.startAnimation();
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                btn_save.revertAnimation();
                goToDetailActivity();
            }
        }.start();
    }

}
