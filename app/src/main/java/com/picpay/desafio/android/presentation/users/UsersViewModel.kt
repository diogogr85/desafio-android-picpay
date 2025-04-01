package com.picpay.desafio.android.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usecase.users.UsersUseCase
import com.picpay.desafio.android.presentation.users.state.UsersState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UsersViewModel(private val usersUseCase: UsersUseCase): ViewModel() {

    private val _usersState = MutableLiveData<UsersState>()
    val usersState: LiveData<UsersState> = _usersState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                loadingState()
                usersUseCase.getUsers()
                    .flowOn(Dispatchers.IO)
                    .collect { list ->
                        _usersState.value = UsersState.Success(list)
                    }
            } catch (exc: Exception) {
                _usersState.value = UsersState.Error(exc.message.toString())
            }
        }
    }

    private fun loadingState() {
        _usersState.value = UsersState.Loading
    }
}