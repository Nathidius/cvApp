package com.example.cvapp.data.model

import com.squareup.moshi.Json

data class WorkExperience(
    @Json(name = "company_name") var companyName: String,
    val cvId: String,
    var from: String,
    val id: String,
    @Json(name = "job_title") var position: String,
    var to: String
) {
    fun areAllDataFielded() = companyName.isNotEmpty()
            && from.isNotEmpty()
            && position.isNotEmpty()
            && to.isNotEmpty()
}