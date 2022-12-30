package com.example.restapitest.di

import android.app.Activity
import com.example.restapitest.MainActivity
import com.example.restapitest.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun apiService(): ApiClient = ApiClient

    @Provides
    @Singleton
    fun provideActivity(): Activity = MainActivity()
}