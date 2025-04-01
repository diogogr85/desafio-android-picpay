package com.picpay.desafio.android.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("profile_image") val profileImage: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("username") val username: String
)
