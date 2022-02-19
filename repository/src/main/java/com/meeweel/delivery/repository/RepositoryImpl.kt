package com.meeweel.delivery.repository

import com.meeweel.delivery.model.entities.DataModel

class RepositoryImpl(
    private val remote: DataSource<List<DataModel>>,
    private val local: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(isOnline: Boolean, category: DataModel.Form): List<DataModel> {
        val data = if (isOnline) {
            remote
        } else {
            local
        }.getData()
        val newData = mutableListOf<DataModel>()
        for (i in 0 until data.size) {
            if (data[i].form == category) {
                newData.add(data[i])
            }
        }
        return newData as List<DataModel>
    }

    override fun insert(list: List<DataModel>) {
        local.insertData(list)
    }
}