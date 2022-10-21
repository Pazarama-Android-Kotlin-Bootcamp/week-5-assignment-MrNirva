package com.merttoptas.retrofittutorial.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.merttoptas.retrofittutorial.data.local.database.base.BaseDao
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.local.database.entity.UsersEntity
import com.merttoptas.retrofittutorial.utils.Constants

@Dao
interface UsersDao : BaseDao<UsersEntity> {

    @Query("SELECT * FROM ${Constants.TABLE_USER_NAME}")
    fun getAllUsers(): List<UsersEntity>

    @Query("DELETE FROM ${Constants.TABLE_USER_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_USER_NAME} WHERE id = :userId")
    fun getUsersById(userId: String): UsersEntity?

}