package io.minoro75.heremapsweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.minoro75.heremapsweatherapp.cityWeatherFragment.CityWeatherFragment

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
