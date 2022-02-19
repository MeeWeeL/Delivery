package com.meeweel.delivery.repository


interface DataSource<T> {

    fun insertData(list: T)
    suspend fun getData() : T
}