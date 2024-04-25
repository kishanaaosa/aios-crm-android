package com.ecommercwebsite.aioscrm.di

import com.ecommercwebsite.aioscrm.network.ApiInterface
import com.ecommercwebsite.aioscrm.ui.login.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideHomeRepository(apiInterface: ApiInterface) = LoginRepository(apiInterface)
}