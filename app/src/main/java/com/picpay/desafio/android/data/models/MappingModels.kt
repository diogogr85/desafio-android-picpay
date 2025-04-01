package com.picpay.desafio.android.data.models

import com.picpay.desafio.android.data.local.entity.UserEntity

fun UserEntity.toUser() = User(
    id = id,
    name = name,
    username = username,
    profileImage = profileImage,
)

fun List<UserEntity>.toListUser(): List<User> = map { it.toUser() }

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    username = username,
    profileImage = profileImage,
)

fun List<User>.toListEntity(): List<UserEntity> = map { it.toEntity() }

