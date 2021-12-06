package io.minoro75.demoweatherapp.forecast_entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecasts(
    var forecastLocation: ForecastLocation
)
