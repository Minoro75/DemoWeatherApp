package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.BuildConfig
import io.minoro75.demoweatherapp.domain.cities_suggestions.model.SuggestionsResponse
import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import io.minoro75.demoweatherapp.domain.geocoding.model.CityNameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/data/2.5/onecall?units=metric&exclude=current,minutely,hourly,alert&appid=${BuildConfig.API_KEY_OPENWEATHER}")
    suspend fun getForecastFromCoordinates(
        @Query(value = "lat") lat: Double,
        @Query(value = "lon") lon: Double
    ): ForecastResponse

    @GET("https://public.opendatasoft.com/api/records/1.0/search/?dataset=geonames-all-cities-with-a-population-1000")
    suspend fun getCitySuggestions(
        @Query(value = "q") enteredQuery: String
    ): SuggestionsResponse

    @GET("/geo/1.0/reverse?appid=${BuildConfig.API_KEY_OPENWEATHER}")
    suspend fun getCityNameFromCoordinates(
        @Query(value = "lat") lat: Double,
        @Query(value = "lon") lon: Double
    ): CityNameResponse
}
