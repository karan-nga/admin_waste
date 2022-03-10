package com.rawtooth.getindustrydetails

data class GetIndustryDetailsModelItem(
    val address: String,
    val description: String,
    val email: String,
    val id: Int,
    val industryType: String,
    val name: String,
    val sector: String
)