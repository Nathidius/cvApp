package com.example.cvapp.data.repository

import com.example.cvapp.data.model.CV
import retrofit2.Response

interface CVRepositoryInterface {

    suspend fun getAllCV(): List<CV>

    suspend fun getCV(id: String): CV

    suspend fun addCV(cv: CV)

    suspend fun deleteCV(id: String)

}