package com.picpay.desafio.android.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val profileImage: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
) : Parcelable