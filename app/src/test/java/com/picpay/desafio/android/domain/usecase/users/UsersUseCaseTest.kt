package com.picpay.desafio.android.domain.usecase.users

import com.picpay.desafio.android.USERS
import com.picpay.desafio.android.domain.repository.PicPayRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersUseCaseTest {
    private val repository = mockk<PicPayRepository>(relaxed = true)
    private val usersUseCase = UsersUseCaseImpl(repository)

    @Test
    fun `should call repository getUsers when useCase getUsers is called`() {
        usersUseCase.getUsers()

        verify(exactly = 1) { repository.getUsers() }
    }

    @Test
    fun `should collect users from repository`() = runBlocking {
        coEvery { repository.getUsers() } returns flow { emit(USERS) }

        usersUseCase.getUsers().collectLatest { list ->
            assertEquals(USERS, list)
        }
    }
}