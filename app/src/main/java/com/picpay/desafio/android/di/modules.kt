package com.picpay.desafio.android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.local.MemoryCache
import com.picpay.desafio.android.data.local.PicPayDatabase
import com.picpay.desafio.android.data.local.PicPayDatabase.Companion.DATABASE_NAME
import com.picpay.desafio.android.data.local.PicPayPrefs
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.network.createService
import com.picpay.desafio.android.data.network.picpay.PicPayService
import com.picpay.desafio.android.data.network.provideApiClient
import com.picpay.desafio.android.data.network.provideLoggingInterceptor
import com.picpay.desafio.android.data.network.provideOkHttpClient
import com.picpay.desafio.android.data.network.repositoriy.PicPayRepositoryImpl
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.domain.usecase.users.UsersUseCase
import com.picpay.desafio.android.domain.usecase.users.UsersUseCaseImpl
import com.picpay.desafio.android.presentation.users.UsersViewModel
import com.picpay.desafio.android.presentation.users.adapters.UserListAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private const val SHARED_PREFS_FILENAME = "picpay-sharedpreferences"

val appModules = module {
    single<MemoryCache> { MemoryCache() }
    single<PicPayDatabase> { provideDataBase(get()) }
    single<UserDao> { get<PicPayDatabase>().userDao() }
    single<PicPayPrefs> { PicPayPrefs(get<Application>().getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE)) }
}

val networkModules = module {
    factory<HttpLoggingInterceptor> { provideLoggingInterceptor() }
    factory<OkHttpClient> { provideOkHttpClient(get()) }
    single<Retrofit> { provideApiClient(get()) }
}

val apiServiceModules = module {
    factory<PicPayService> { createService(get(), PicPayService::class.java) }
}

val repositoryModules = module {
    factory<PicPayRepository> { PicPayRepositoryImpl(get(), get(), get(), get()) }
}

val useCaseModules = module {
    factory<UsersUseCase> { UsersUseCaseImpl(get()) }
}

val viewModelModules = module {
    viewModel<UsersViewModel> { UsersViewModel(get()) }
}

val adapterModules = module {
    factory<UserListAdapter> { UserListAdapter() }
}

private fun provideDataBase(application: Application): PicPayDatabase =
    Room.databaseBuilder(
        application,
        PicPayDatabase::class.java,
        DATABASE_NAME
    ).build()