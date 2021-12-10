package io.minoro75.demoweatherapp.domain.cities_suggestions.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Suggestions(
    @Json(name = "fields") var suggestions: SuggestionItem? = null
)
