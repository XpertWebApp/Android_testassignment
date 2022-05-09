package com.app.peyza.di

import com.app.peyza.networks.Respository
import com.app.peyza.views.modules.loginModule.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun loginViewModel(repo: Respository) = LoginViewModel(repo)

}