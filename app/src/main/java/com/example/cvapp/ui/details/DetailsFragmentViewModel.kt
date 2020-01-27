package com.example.cvapp.ui.details

import androidx.lifecycle.MutableLiveData
import com.example.cvapp.data.model.CV
import com.example.cvapp.data.repository.CVRepository
import com.example.cvapp.ui.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class DetailsFragmentViewModel : BaseViewModel() {

    private val cvRepository: CVRepository by inject()

    val cv = MutableLiveData<CV>()

    fun getCVDetails(cvId: String) {
        launch {
            withContext(Dispatchers.IO) {
                cv.postValue(cvRepository.getCV(cvId))
            }
        }
    }

}