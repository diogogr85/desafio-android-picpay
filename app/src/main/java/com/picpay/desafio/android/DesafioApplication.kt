package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.apiServiceModules
import com.picpay.desafio.android.di.networkModules
import com.picpay.desafio.android.di.repositoryModules
import com.picpay.desafio.android.di.useCaseModules
import com.picpay.desafio.android.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DesafioApplication)
            modules(
                networkModules,
                apiServiceModules,
                repositoryModules,
                useCaseModules,
                viewModelModules
            )
        }
    }
}