package io.minoro75.demoweatherapp.domain.forecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "lat") var latitude: Double? = null,
    @Json(name = "lon") var longitude: Double? = null,
    @Json(name = "daily") var forecastList: List<Forecast> = arrayListOf()
)
