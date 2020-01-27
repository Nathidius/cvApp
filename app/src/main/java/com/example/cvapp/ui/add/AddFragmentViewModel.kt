package com.example.cvapp.ui.add

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import com.example.cvapp.data.model.CV
import com.example.cvapp.data.model.WorkExperience
import com.example.cvapp.data.repository.CVRepository
import com.example.cvapp.ui.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

class AddFragmentViewModel : BaseViewModel() {

    private val cvRepository: CVRepository by inject()

    val cv = CV("", "", "", "", "", "", "", "", 0, "", "", "", listOf())
    val cvLiveData = MutableLiveData<CV>(cv)
    val shouldNavigateBack = MutableLiveData<Boolean>(false)

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validateData()
        }
    }

    fun onAddWorkExperienceClicked() {
        cv.workExperience =
            listOf(WorkExperience("", cv.id, "", "", "", "")).plus(cv.workExperience)
        validateData()
    }

    fun onAddCVClicked() {
        launch {
            withContext(Dispatchers.IO) {
                cvRepository.addCV(cv)
            }
            shouldNavigateBack.postValue(true)
        }
    }

    fun validateData() {
        cvLiveData.postValue(cv)
    }

}