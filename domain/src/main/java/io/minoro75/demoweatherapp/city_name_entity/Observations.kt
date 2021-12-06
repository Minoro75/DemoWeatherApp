package io.minoro75.demoweatherapp.city_name_entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Observations(
    val location: List<ObservationLocation>
)
