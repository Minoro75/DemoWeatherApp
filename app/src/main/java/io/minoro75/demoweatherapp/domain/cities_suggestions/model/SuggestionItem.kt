package io.minoro75.demoweatherapp.domain.cities_suggestions.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuggestionItem(
    @Json(name = "name") var cityName: String? = null,
    var coordinates: List<Double> = arrayListOf(),
    @Json(name = "country_code") var countryCode: String? = null
)
