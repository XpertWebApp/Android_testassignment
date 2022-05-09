package com.app.peyza.di

import android.content.Context
import com.app.peyza.networks.Api
import com.app.peyza.networks.NetworkConnectionInterceptor
import com.app.peyza.networks.Respository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun networkInterceptor(@ApplicationContext context: Context) =
        NetworkConnectionInterceptor(context)

    @Provides
    @Singleton
    fun networkConnection(@ApplicationContext context: Context) =
        Api.invoke(NetworkConnectionInterceptor(context))


    @Provides
    @Singleton
    fun repository(api: Api) = Respository(api = api)
}