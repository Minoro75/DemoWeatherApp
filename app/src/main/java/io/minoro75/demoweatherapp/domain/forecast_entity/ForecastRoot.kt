package io.minoro75.demoweatherapp.domain.forecast_entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastRoot(
    var forecasts: Forecasts,
    var feedCreation: String,
    var metric: Boolean
)
