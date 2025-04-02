package com.picpay.desafio.android.data.local

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [UserEntity::class], version = 1)
abstract class PicPayDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "picpay_database"

//        @Volatile
//        private var INSTANCE: PicPayDatabase? = null
//        fun getDatabase(context: Context, scope: CoroutineScope): PicPayDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PicPayDatabase::class.java,
//                    DATABASE_NAME
//                ).build()
//
//                INSTANCE = instance
//                instance
//            }
//        }
    }

    fun provideDataBase(application: Application): PicPayDatabase =
        Room.databaseBuilder(
            application,
            PicPayDatabase::class.java,
            DATABASE_NAME
        ).
        fallbackToDestructiveMigration().build()
}