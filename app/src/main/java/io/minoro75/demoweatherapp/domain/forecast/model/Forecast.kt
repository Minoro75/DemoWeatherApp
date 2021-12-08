package io.minoro75.demoweatherapp.domain.forecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "dt") var currentTime: Int? = null,
    @Json(name = "temp") var temperature: Temperature? = null,
    // tbh idk why they send it as list, it's always 1 item array \o/
    @Json(name = "weather") var iconList: List<ForecastIcon> = arrayListOf()
)
