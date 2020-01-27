package com.example.cvapp.di

import com.example.cvapp.data.repository.CVRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.dsl.module

val repositoryModule = module {

    single { CVRepository(get()) }

    single {
        CoroutineExceptionHandler { _, t ->
            t.printStackTrace()
        }
    }

}