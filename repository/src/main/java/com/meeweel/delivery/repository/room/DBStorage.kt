package com.meeweel.delivery.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meeweel.delivery.model.entities.RoomDTO

@Database(entities = [RoomDTO::class], version = 1, exportSchema = false)
abstract class DBStorage : RoomDatabase() {

    abstract fun getEntityDao(): EntityDao

}