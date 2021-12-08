package io.minoro75.demoweatherapp.domain.forecast.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastIcon(
    @Json(name = "description") var iconDescription: String? = null,
    // icon link need to be added to base url before usage
    @Json(name = "icon") var iconLink: String? = null
)
