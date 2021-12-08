package io.minoro75.demoweatherapp.domain.forecast.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temperature(
    var min: Double? = null,
    var max: Double? = null
)
