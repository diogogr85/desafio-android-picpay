package com.picpay.desafio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.picpay.desafio.android.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("DELETE FROM UserEntity")
    fun deleteAll()
}