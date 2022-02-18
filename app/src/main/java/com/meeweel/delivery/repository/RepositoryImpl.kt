package com.meeweel.delivery.repository

import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.network.RemoteDataSourceImpl
import com.meeweel.delivery.room.LocalDataSourceImpl
import kotlinx.coroutines.*

class RepositoryImpl(
    private val remote: DataSource<List<DataModel>>,
    private val local: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(isOnline: Boolean): List<DataModel> {
        return if (isOnline) {
            remote
        } else {
            local
        }.getData()
    }

    override fun insert(list: List<DataModel>) {
        local.insertData(list)
    }
}