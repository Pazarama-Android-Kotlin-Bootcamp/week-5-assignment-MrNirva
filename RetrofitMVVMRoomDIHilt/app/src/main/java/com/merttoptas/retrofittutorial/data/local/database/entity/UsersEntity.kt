package com.merttoptas.retrofittutorial.data.local.database.entity

import androidx.room.*
import com.merttoptas.retrofittutorial.data.local.database.converter.DaoConverter
import com.merttoptas.retrofittutorial.data.model.Address
import com.merttoptas.retrofittutorial.data.model.Company
import com.merttoptas.retrofittutorial.utils.Constants

@Entity(tableName = Constants.TABLE_USER_NAME)
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int?,
    @Embedded(prefix = "address") val address: Address?,
    @Embedded(prefix = "company") val company: Company?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "website") val website: String?,
)
