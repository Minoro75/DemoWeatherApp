package io.minoro75.heremapsweatherapp.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecasts(
    var forecastLocation: ForecastLocation
)
