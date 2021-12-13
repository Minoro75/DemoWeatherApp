package io.minoro75.demoweatherapp.domain.geocoding.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityNameResponse(
    @Json(name = "name") var cityName: String? = null
)
