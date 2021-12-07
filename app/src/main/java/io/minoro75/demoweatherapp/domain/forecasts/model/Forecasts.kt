package io.minoro75.demoweatherapp.domain.forecasts.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecasts(
    var forecastLocation: ForecastLocation
)
