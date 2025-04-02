package com.picpay.desafio.android.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class PicPayPrefs(private val sharedPrefs: SharedPreferences) {
    var dbTimestamp: Long
        get() = sharedPrefs.getLong("db-expiration-timestamp", -1)
        set(value) = sharedPrefs.edit { putLong("db-expiration-timestamp", value) }
}