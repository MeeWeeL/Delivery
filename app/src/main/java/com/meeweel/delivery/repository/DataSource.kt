package com.meeweel.delivery.repository

import com.meeweel.delivery.model.DataModel

interface DataSource<T> {

    fun insertData(list: List<DataModel>)
    suspend fun getData() : List<DataModel>
}