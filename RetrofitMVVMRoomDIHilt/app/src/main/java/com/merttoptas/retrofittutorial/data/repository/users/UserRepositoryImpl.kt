package com.merttoptas.retrofittutorial.data.repository.users

import com.merttoptas.retrofittutorial.data.local.database.UsersDatabase
import com.merttoptas.retrofittutorial.data.local.database.entity.UsersEntity
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.remote.api.ApiService
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val apiService: ApiService,
    private val usersDatabase: UsersDatabase
) : UserRepository {

    override fun getUsers(): Call<List<Users>> {
        return apiService.getUsers()
    }

    override fun getUserById(id: Int): UsersEntity? {
        return usersDatabase.usersDao().getUsersById(id.toString())
    }

    override fun insertFavoriteUser(user: UsersEntity) {
        return usersDatabase.usersDao().insert(user)
    }

    override fun deleteFavoriteUser(user: UsersEntity) {
        return usersDatabase.usersDao().delete(user)
    }

}