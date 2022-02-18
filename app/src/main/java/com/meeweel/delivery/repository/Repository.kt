package com.meeweel.delivery.repository

import com.meeweel.delivery.model.DataModel

interface Repository {

    fun insertData(list: List<DataModel>)
    suspend fun getData() : List<DataModel>
}