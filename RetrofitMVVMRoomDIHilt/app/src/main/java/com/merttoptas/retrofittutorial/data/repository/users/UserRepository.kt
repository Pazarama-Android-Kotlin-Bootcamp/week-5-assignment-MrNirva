package com.merttoptas.retrofittutorial.data.repository.users

import com.merttoptas.retrofittutorial.data.local.database.entity.UsersEntity
import com.merttoptas.retrofittutorial.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
    fun getUserById(id: Int): UsersEntity?
    fun insertFavoriteUser(user: UsersEntity)
    fun deleteFavoriteUser(user: UsersEntity)
}