package com.meeweel.delivery.repository

import com.meeweel.delivery.model.entities.DataModel

interface Repository {

    fun insert(list: List<DataModel>)
    suspend fun getData(isOnline: Boolean) : List<DataModel>
}