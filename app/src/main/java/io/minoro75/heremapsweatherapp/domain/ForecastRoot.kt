package io.minoro75.heremapsweatherapp.domain

data class ForecastRoot(
    val feedCreation: String,
    val forecasts: Forecasts,
    val metric: Boolean
)
