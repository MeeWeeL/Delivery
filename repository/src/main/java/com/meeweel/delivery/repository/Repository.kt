package com.meeweel.delivery.repository


interface Repository<T> {

    fun insert(list: T)
    suspend fun getData(isOnline: Boolean) : T
}