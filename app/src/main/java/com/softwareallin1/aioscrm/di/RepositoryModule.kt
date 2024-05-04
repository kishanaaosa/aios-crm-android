package com.softwareallin1.aioscrm.di

import com.softwareallin1.aioscrm.network.ApiInterface
import com.softwareallin1.aioscrm.ui.login.repository.LoginRepository
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