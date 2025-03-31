package com.picpay.desafio.android.domain.usecase.users

import com.picpay.desafio.android.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
    fun getUsers(): Flow<List<User>>
}