package com.rawtooth.getngodetails

data class GetNgoDetailsModelItem(
    val description: String,
    val emailId: Any,
    val location: String,
    val name: String,
    val ngoId: Int,
    val ngoType: String,
    val picByte: String
)