package com.example.cvapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.cvapp.data.model.CV
import com.example.cvapp.data.repository.CVRepository
import com.example.cvapp.ui.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class HomeFragmentViewModel: BaseViewModel() {

    private val cvRepository: CVRepository by inject()
    val cvList = MutableLiveData<List<CV>>()

    fun getAllCV() {
        launch {
            withContext(Dispatchers.IO) {
                cvList.postValue(cvRepository.getAllCV())
            }
        }
    }

    fun deleteCV(cvId: String) {
        launch {
            withContext(Dispatchers.IO) {
                cvRepository.deleteCV(cvId)
            }
            getAllCV()
        }
    }
}