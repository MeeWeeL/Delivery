package com.meeweel.delivery.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomDTO(

    @PrimaryKey(autoGenerate = false)
    val name: String,
    val category: String,
    val description: String,
    val price: Int
)