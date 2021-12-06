package io.minoro75.demoweatherapp.domain.forecast_entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastLocation(
    var forecast: List<Weather>,
    var country: String,
    var state: String,
    var city: String,
    var latitude: Double,
    var longitude: Double,
    var distance: Int,
    var timezone: Int
)
