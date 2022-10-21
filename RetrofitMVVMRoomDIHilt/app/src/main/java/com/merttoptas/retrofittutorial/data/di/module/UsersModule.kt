package com.merttoptas.retrofittutorial.data.di.module

import com.merttoptas.retrofittutorial.data.local.database.UsersDatabase
import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import com.merttoptas.retrofittutorial.data.repository.users.UserRepository
import com.merttoptas.retrofittutorial.data.repository.users.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {
    // Api Service Class
    // Repository and DataSource Impl
    // Database

    @Provides
    fun provideUsersRepository(apiService: ApiService, usersDatabase: UsersDatabase) : UserRepository {
        return UserRepositoryImpl(apiService, usersDatabase)
    }

}