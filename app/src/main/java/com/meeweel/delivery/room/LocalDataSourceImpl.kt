package com.meeweel.delivery.room

import androidx.room.Room
import com.meeweel.delivery.app.App
import com.meeweel.delivery.model.Mapper
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.repository.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LocalDataSourceImpl(
    dbStorage: DBStorage = Room.databaseBuilder(
        App.ContextHolder.context,
        DBStorage::class.java,
        "EntityDB"
    ).build()
) : DataSource<List<DataModel>> {

    private val map = Mapper()
    private val db = dbStorage.getEntityDao()

    override fun insertData(list: List<DataModel>) {
        db.insertList(map.convertDataModelListToRoomDTOList(list))
    }

    override suspend fun getData(): List<DataModel> {
        return map.convertRoomDTOListToDataModelList(db.getAll())
    }


}