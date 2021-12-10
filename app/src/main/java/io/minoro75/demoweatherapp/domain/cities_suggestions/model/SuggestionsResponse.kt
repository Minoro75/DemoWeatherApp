package io.minoro75.demoweatherapp.domain.cities_suggestions.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SuggestionsResponse(
    var records: List<Suggestions> = arrayListOf()
)
