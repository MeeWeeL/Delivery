package com.meeweel.delivery.model

import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.model.entities.NetworkDTO
import com.meeweel.delivery.model.entities.RoomDTO
import org.junit.Assert
import org.junit.Test

class MapperTest {

    private val map = Mapper()

    @Test
    fun validator_CorrectMappingNetworkToDataModelNotRoomDTO_ReturnsTrue() {
        Assert.assertFalse(
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ).javaClass.isAssignableFrom(RoomDTO::class.java)
        )
    }

    @Test

    fun validator_CorrectMappingNetworkToDataModel_ReturnsTrue() {
        Assert.assertFalse(
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ).javaClass.isAssignableFrom(DataModel::class.java)
        )
    }

}