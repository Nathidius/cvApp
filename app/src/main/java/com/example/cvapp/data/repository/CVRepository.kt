package com.example.cvapp.data.repository

import com.example.cvapp.data.model.CV
import com.example.cvapp.network.ApiClient

class CVRepository(private val apiClient: ApiClient) : CVRepositoryInterface {

    override suspend fun getAllCV() = apiClient.getCVList()

    override suspend fun getCV(id: String): CV = apiClient.getCVDetails(id)

    override suspend fun addCV(cv: CV) {
        apiClient.addCV(cv)
    }

    override suspend fun deleteCV(id: String) {
        apiClient.deleteCV(id)
    }
}