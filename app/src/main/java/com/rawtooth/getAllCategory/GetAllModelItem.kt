package com.rawtooth.getAllCategory

data class GetAllModelItem(
    val categoryName: String,
    val description: String,
    val id: Int,
    val picByte: String,
    val wastes: List<Any>
)