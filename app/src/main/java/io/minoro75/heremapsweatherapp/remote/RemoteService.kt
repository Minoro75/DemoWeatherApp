package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.BuildConfig
import io.minoro75.heremapsweatherapp.domain.ForecastLocation
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/weather/1.0/report.json?product=forecast_7days&apiKey=${BuildConfig.API_KEY}")
    suspend fun getWeatherInCity(
        @Query(value = "name") city: String
    ): ForecastLocation
}
