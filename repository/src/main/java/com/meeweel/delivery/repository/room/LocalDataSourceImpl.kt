package com.meeweel.delivery.repository.room


import com.meeweel.delivery.model.Mapper
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.repository.DataSource

class LocalDataSourceImpl(dbStorage: DBStorage) :
    DataSource<List<DataModel>> {
    private val map = Mapper()
    private val db = dbStorage.getEntityDao()

    override fun insertData(list: List<DataModel>) {
        db.insertList(map.convertDataModelListToRoomDTOList(list))
    }

    override suspend fun getData(): List<DataModel> {
        return map.convertRoomDTOListToDataModelList(db.getAll())
    }


}