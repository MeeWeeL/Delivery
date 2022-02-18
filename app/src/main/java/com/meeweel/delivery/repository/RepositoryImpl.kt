package com.meeweel.delivery.repository

import com.meeweel.delivery.model.DataModel
import com.meeweel.delivery.network.RemoteDataSourceImpl

class RepositoryImpl(private val remote: DataSource<List<DataModel>> = RemoteDataSourceImpl()) : Repository {

    override fun insertData(list: List<DataModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getData(): List<DataModel> {
        return remote.getData()
    }
}