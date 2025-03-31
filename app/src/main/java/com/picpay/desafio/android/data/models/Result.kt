package com.picpay.desafio.android.data.models

sealed class Result<out T> {
    class Success<out T>(val result: T): Result<T>()
    class Error(val exception: Exception) : Result<Nothing>()
}