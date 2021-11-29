package io.minoro75.heremapsweatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.ui.city_weather.CityWeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CityWeatherFragment.newInstance())
                .commitNow()
        }
    }
}
