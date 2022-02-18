package com.meeweel.delivery.model

class DataModel(
    val title: String,
    val description: String,
    val picture: String,
    val price: Int,
    val form: Form
) {
    enum class Form {
        PIZZA,
        COMBO,
        WATER,
        DESERT

    }
}