package com.merttoptas.retrofittutorial.data.di

import android.content.Context
import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.local.database.UsersDatabase
import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    // Room Database
    // Dao
    // DataStoreManager
    // SharedPreferences

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostsDatabase(@ApplicationContext appContext: Context): PostsDatabase {
        return PostsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostsDatabase) = db.postDao()


    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return UsersDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: UsersDatabase) = db.usersDao()

}