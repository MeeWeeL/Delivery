package com.meeweel.delivery.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meeweel.delivery.model.entities.RoomDTO

@Dao
interface EntityDao {

    @Query("SELECT * FROM RoomDTO")
    fun getAll(): List<RoomDTO>

    @Query("SELECT * FROM RoomDTO WHERE name LIKE :text")
    fun getWordByText(text: String): List<RoomDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: RoomDTO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(entities: List<RoomDTO>)

}