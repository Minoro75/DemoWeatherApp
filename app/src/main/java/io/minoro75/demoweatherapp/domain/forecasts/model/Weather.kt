package io.minoro75.demoweatherapp.domain.forecasts.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Weather(
    var daylight: String,
    var daySegment: String,
    var description: String,
    var skyInfo: String,
    var skyDescription: String,
    var temperature: String,
    var temperatureDesc: String,
    var comfort: String,
    var humidity: String,
    var dewPoint: String,
    var precipitationProbability: String,
    var precipitationDesc: String,
    var rainFall: String,
    var snowFall: String,
    var airInfo: String,
    var airDescription: String,
    var windSpeed: String,
    var windDirection: String,
    var windDesc: String,
    var windDescShort: String,
    var beaufortScale: String,
    var beaufortDescription: String,
    var visibility: String,
    var icon: String,
    var iconName: String,
    var iconLink: String,
    var dayOfWeek: String,
    var utcTime: String
)
