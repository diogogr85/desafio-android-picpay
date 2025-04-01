package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface PicPayRepository {
    fun getUsers(): Flow<List<User>>
}