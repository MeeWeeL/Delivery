package com.meeweel.delivery.model

data class NetworkDTO(
    val category: String,
    val name: String,
    val topping: List<String>?,
    val price: Int
)