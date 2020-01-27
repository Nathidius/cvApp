package com.example.cvapp.ui.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(),
    CoroutineScope, KoinComponent {

    private val coroutineExceptionHandler: CoroutineExceptionHandler by inject()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + coroutineExceptionHandler

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}