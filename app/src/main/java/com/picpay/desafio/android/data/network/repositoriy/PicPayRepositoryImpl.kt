package com.picpay.desafio.android.data.network.repositoriy

import com.picpay.desafio.android.data.helper.TimeHelper
import com.picpay.desafio.android.data.local.MemoryCache
import com.picpay.desafio.android.data.local.PicPayPrefs
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.data.models.toListEntity
import com.picpay.desafio.android.data.models.toListUser
import com.picpay.desafio.android.data.network.picpay.PicPayService
import com.picpay.desafio.android.domain.repository.PicPayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PicPayRepositoryImpl(
    private val picPayService: PicPayService,
    private val cache: MemoryCache,
    private val userDao: UserDao,
    private val prefs: PicPayPrefs,
    private val timeHelper: TimeHelper = TimeHelper()
): PicPayRepository {
    override fun getUsers(): Flow<List<User>> = flow {
        var users = if (isDbExpired()) {
            userDao.deleteAll()
            fetchUsers()
        } else {
            cache.getListInCache()
        }

        if (users.isNullOrEmpty()) {
            users = getUsersFromDatabase()
        }

        emit(users)
    }

    private suspend fun getUsersFromDatabase(): List<User> {
        var users = userDao.getAll().toListUser()

        if (users.isEmpty()) {
            users = fetchUsers()
        }

        return users
    }

    private suspend fun fetchUsers(): List<User> {
        val users = picPayService.getUsers()

        prefs.dbTimestamp = timeHelper.getCurrentTimestamp()
        userDao.insertAll(users.toListEntity())
        cache.updateListInCache(users)

        return users
    }

    private fun isDbExpired() = (timeHelper.getCurrentTimestamp() - prefs.dbTimestamp) > DB_EXPIRATION_TIME

    private companion object {
        const val DB_EXPIRATION_TIME = 30000
    }
}