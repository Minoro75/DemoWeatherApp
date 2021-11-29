package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.BuildConfig
import io.minoro75.heremapsweatherapp.domain.city_name_entity.CityNameRoot
import io.minoro75.heremapsweatherapp.domain.forecast_entity.ForecastRoot
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/weather/1.0/report.json?product=forecast_7days&apiKey=${BuildConfig.API_KEY}")
    suspend fun getWeatherInCity(
        @Query(value = "name") city: String
    ): ForecastRoot

    @GET("/weather/1.0/report.json?product=observation&apiKey=${BuildConfig.API_KEY}&oneobservation=true")
    suspend fun getCityNameFromCoordinates(
        @Query(value = "latitude") lat: Double,
        @Query(value = "longitude") lon: Double
    ): CityNameRoot
}
