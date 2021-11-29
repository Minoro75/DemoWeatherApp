package io.minoro75.heremapsweatherapp.domain

data class ForecastLocation(
    val city: String,
    val country: String,
    val distance: Int,
    val forecast: List<Weather>,
    val latitude: Double,
    val longitude: Double,
    val state: String,
    val timezone: Int
)
