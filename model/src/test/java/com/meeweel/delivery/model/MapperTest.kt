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

    @Test
    fun validator_EqualsMappingTest_ReturnsTrue() {
        val first =
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            )
        val second = first
        Assert.assertEquals(first, second)
    }

    @Test
    fun validator_NotEqualsMappingTestl_ReturnsTrue() {
        Assert.assertNotEquals(
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ),
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            )
        )
    }

    @Test
    fun validator_ArrayEqualsMappingTest_ReturnsTrue() {
        val arr1 = arrayOf(
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ),
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ))
        val arr2 = arr1
        Assert.assertArrayEquals(arr1, arr2)
    }

    @Test
    fun validator_NullMappingTest_ReturnsTrue() {
        Assert.assertNull(
            NetworkDTO(
                "drynk",
                "Cola",
                null,
                29
            ).topping
        )
    }

    @Test
    fun validator_NotNullMappingTest_ReturnsTrue() {
        Assert.assertNotNull(
            map.convertNetworkDTOToDataModel(
                NetworkDTO(
                    "drynk",
                    "Cola",
                    null,
                    29
                )
            ).picture
        )
    }

    @Test
    fun validator_SameMappingTest_ReturnsTrue() {
        val thing = map.convertNetworkDTOToDataModel(
        NetworkDTO(
            "drynk",
            "Cola",
            null,
            29
        )
        )
        Assert.assertSame(thing,thing)
    }
}