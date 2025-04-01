package com.picpay.desafio.android.domain.usecase.users

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.domain.repository.PicPayRepository
import kotlinx.coroutines.flow.Flow

class UsersUseCaseImpl(private val picPayRepository: PicPayRepository): UsersUseCase {
    override fun getUsers(): Flow<List<User>> {
        return picPayRepository.getUsers()
    }
}