package io.minoro75.demoweatherapp.domain.geocoding.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesResponse(
    var name: String? = null,
    @Json(name = "lat") var latitude: Double? = null,
    @Json(name = "lon") var longitude: Double? = null,
    var country: String? = null
)
