package com.picpay.desafio.android.data.network.repositoriy

import com.picpay.desafio.android.USERS
import com.picpay.desafio.android.USERS_ENTITY
import com.picpay.desafio.android.data.helper.TimeHelper
import com.picpay.desafio.android.data.local.MemoryCache
import com.picpay.desafio.android.data.local.PicPayPrefs
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.models.toListUser
import com.picpay.desafio.android.data.network.picpay.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PicPayRepositoryTest {

    private val apiService = mockk<PicPayService>(relaxed = true)
    private val cache = mockk<MemoryCache>(relaxed = true)
    private val userDao = mockk<UserDao>(relaxed = true)
    private val prefs = mockk<PicPayPrefs>(relaxed = true)
    private val timeHelper = mockk<TimeHelper>(relaxed = true)
    private val repository = PicPayRepositoryImpl(apiService, cache, userDao, prefs, timeHelper)

    @Test
    fun `should fetch users from apiService when cache is expired`() = runBlocking {
        coEvery { apiService.getUsers() } returns USERS

        val result = repository.getUsers().single()

        assertEquals(USERS, result)
        coVerify { apiService.getUsers() }
        coVerify(exactly = 0) { cache.getListInCache() }
        coVerify(exactly = 0) { userDao.getAll() }
    }

    @Test
    fun `should get users cached when db is not expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 100000
        coEvery { cache.getListInCache() } returns USERS
        every { timeHelper.getCurrentTimestamp() } returns 110000

        val result = repository.getUsers().single()

        assertEquals(USERS, result)
        coVerify(exactly = 0) { apiService.getUsers() }
        coVerify(exactly = 1) { cache.getListInCache() }
        coVerify(exactly = 0) { userDao.getAll() }
    }

    @Test
    fun `should get users from database when cache fails and db is not expired`() = runBlocking {
        every { prefs.dbTimestamp } returns 100000
        coEvery { userDao.getAll() } returns USERS_ENTITY
        every { timeHelper.getCurrentTimestamp() } returns 110000

        val result = repository.getUsers().single()

        assertEquals(USERS, result)
        coVerify(exactly = 0) { apiService.getUsers() }
        coVerify(exactly = 1) { cache.getListInCache() }
        coVerify(exactly = 1) { userDao.getAll() }
    }
}