package com.example.loginandregistercompoase.di

import com.example.loginandregistercompoase.utils.SampleLoginDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideSlimeDispatchers(): SampleLoginDispatchers {
        return SampleLoginDispatchers(
            default = Dispatchers.Default,
            main = Dispatchers.Main,
            io = Dispatchers.IO
        )
    }


}