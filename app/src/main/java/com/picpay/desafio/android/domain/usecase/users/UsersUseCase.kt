package com.picpay.desafio.android.domain.usecase.users

import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
    fun getUsers(): Flow<List<User>>
}