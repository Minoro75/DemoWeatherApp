package io.minoro75.heremapsweatherapp.domain.city_name_entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityNameRoot(
    val observations: Observations,
    val feedCreation: String,
    val metric: Boolean
)
