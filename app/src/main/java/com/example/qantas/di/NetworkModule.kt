package com.example.qantas.di

import com.example.qantas.data.ApiClient
import com.example.qantas.data.AirportListRepositoryImp
import com.example.qantas.domain.AirportListRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  fun providesNetworkClient(): Retrofit{
    return Retrofit.Builder()
      .baseUrl("https://api.qantas.com/")
      .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providesApiClient(retrofit: Retrofit): ApiClient {
    return retrofit.create(ApiClient::class.java)
  }

  @Singleton
  @Provides
  fun provideRepository(apiClient: ApiClient): AirportListRepository {
    return AirportListRepositoryImp(apiClient)
  }
}