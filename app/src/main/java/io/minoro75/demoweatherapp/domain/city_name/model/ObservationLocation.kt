package io.minoro75.demoweatherapp.domain.city_name.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ObservationLocation(
    val country: String,
    val state: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val timezone: Int

)