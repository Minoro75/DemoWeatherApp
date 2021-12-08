package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.BuildConfig
import io.minoro75.demoweatherapp.domain.city_name.model.CityNameRoot
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/data/2.5/onecall?units=metric&exclude=current,minutely,hourly,alert&appid=${BuildConfig.API_KEY_OPENWEATHER}")
    suspend fun getForecastFromCoordinates(
        @Query(value = "lat") lat: Double,
        @Query(value = "lon") lon: Double
    ): ForecastResponse

    @GET("/geo/1.0/direct?limit=1&appid=${BuildConfig.API_KEY_OPENWEATHER}")
    suspend fun getCoordinatesFromCityName(
        @Query(value = "q") cityName: String
    ): CoordinatesResponse

    @GET("/weather/1.0/report.json?product=observation&apiKey=${BuildConfig.API_KEY}&oneobservation=true")
    suspend fun getCityNameFromCoordinates(
        @Query(value = "latitude") lat: Double,
        @Query(value = "longitude") lon: Double
    ): CityNameRoot
}
