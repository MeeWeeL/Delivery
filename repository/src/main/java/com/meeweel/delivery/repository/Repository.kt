package com.meeweel.delivery.repository

import com.meeweel.delivery.model.entities.DataModel


interface Repository<T> {

    fun insert(list: T)
    suspend fun getData(isOnline: Boolean, category: DataModel.Form) : T
}