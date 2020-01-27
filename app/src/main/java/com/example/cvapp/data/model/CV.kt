package com.example.cvapp.data.model

import com.squareup.moshi.Json

data class CV(
    @Json(name = "applying_for") var applyingFor: String,
    var photo: String,
    var city: String,
    var country: String,
    var email: String,
    @Json(name = "first_name") var firstName: String,
    val id: String,
    var phone: String,
    var salary: Int,
    @Json(name = "secondary_adress") var secondaryAdress: String,
    @Json(name = "street_adress") var streetAdress: String,
    var surname: String,
    @Json(name = "work_experience") var workExperience: List<WorkExperience>
) {
    fun areAllDataFielded() = applyingFor.isNotEmpty()
            && photo.isNotEmpty()
            && city.isNotEmpty()
            && country.isNotEmpty()
            && email.isNotEmpty()
            && firstName.isNotEmpty()
            && phone.isNotEmpty()
            && salary > 0
            && secondaryAdress.isNotEmpty()
            && streetAdress.isNotEmpty()
            && surname.isNotEmpty()
            && workExperience.size == workExperience.filter { it.areAllDataFielded() }.size
}