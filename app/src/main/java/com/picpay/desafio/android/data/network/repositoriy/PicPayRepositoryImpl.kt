package com.picpay.desafio.android.data.network.repositoriy

import com.picpay.desafio.android.data.network.picpay.PicPayService
import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.repository.PicPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicPayRepositoryImpl(private val picPayService: PicPayService): PicPayRepository {
    override fun getUsers(): Flow<List<User>> = flow {
        emit(picPayService.getUsers())
    }
}