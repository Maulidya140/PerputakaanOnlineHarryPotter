package com.dicoding.latihan_praktikum_10.presentation.ui;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dicoding.latihan_praktikum_10.R;
import com.dicoding.latihan_praktikum_10.presentation.ui.fragment.FavoriteFragment;
import com.dicoding.latihan_praktikum_10.presentation.ui.fragment.HomeFragment;
import com.dicoding.latihan_praktikum_10.presentation.ui.fragment.KontentFragment;
import com.dicoding.latihan_praktikum_10.presentation.ui.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private BottomNavigationView bottomNavigation;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 🔦 Sensor Cahaya
        mainLayout = findViewById(R.id.layout_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment;

            if (item.getItemId() == R.id.menu_home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.menu_konten) {
                fragment = new KontentFragment();
            } else if (item.getItemId() == R.id.menu_favorite) {
                fragment = new FavoriteFragment();
            } else if (item.getItemId() == R.id.menu_setting) {
                fragment = new SettingFragment();
            } else {
                fragment = new HomeFragment();
            }

            loadFragment(fragment);
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightLevel = event.values[0];
        if (lightLevel < 10) {
            mainLayout.setBackgroundColor(Color.BLACK);
        } else {
            mainLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}
