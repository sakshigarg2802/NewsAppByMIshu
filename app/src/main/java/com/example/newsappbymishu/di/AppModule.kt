package com.example.newsappbymishu.di

import android.content.Context
import com.example.newsappbymishu.network.INewsApi
import com.example.newsappbymishu.repo.INewsRepo
import com.example.newsappbymishu.repo.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class AppModule {
    @InstallIn(SingletonComponent::class)
    @Module
    class NetworkModule {
        fun provideINewsApi(): INewsApi {
            return INewsApi.getInstance()
        }

        @Singleton
        @Provides
        fun provideNewsRepository(@ApplicationContext appContext: Context): INewsRepo {
            return NewsRepo(INewsApi.getInstance())
        }
    }
}