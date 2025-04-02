package com.picpay.desafio.android.presentation.users.state

import com.picpay.desafio.android.data.models.User

sealed class UsersState {
    data object Loading : UsersState()
    data class Success(val users: List<User>) : UsersState()
    data class Error(val message: String) : UsersState()
}
