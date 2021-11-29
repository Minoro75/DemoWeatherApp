package io.minoro75.heremapsweatherapp.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.minoro75.heremapsweatherapp.BuildConfig
import io.minoro75.heremapsweatherapp.remote.RemoteDataSourceImpl
import io.minoro75.heremapsweatherapp.remote.RemoteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {
    @Provides
    fun provideBaseUrl() =
        BuildConfig.API_SERVER

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RemoteService =
        retrofit.create(RemoteService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSourceImpl(remoteService: RemoteService): RemoteDataSourceImpl =
        RemoteDataSourceImpl(remoteService)
}
