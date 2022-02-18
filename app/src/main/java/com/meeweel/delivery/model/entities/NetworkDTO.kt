package com.meeweel.delivery.model.entities

data class NetworkDTO(
    val category: String,
    val name: String,
    val topping: List<String>?,
    val price: Int
)