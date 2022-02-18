package com.meeweel.delivery.model

import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.model.entities.NetworkDTO
import com.meeweel.delivery.model.entities.RoomDTO

// Выбрал неудачный API, который не предоставляет картинки
const val pizzaPic = "https://yandex.ru/images/search?text=pizza&lr=10725&pos=50&img_url=https%3A%2F%2Fs3.eu-north-1.amazonaws.com%2Frestoraids%2Fmedia%2Fpizza-1.png&rpt=simage"

class Mapper {
    fun convertNetworkDTOToDataModel(networkDTO: NetworkDTO) : DataModel {
        var pic = pizzaPic
        var form = DataModel.Form.PIZZA
        when (networkDTO.category) {
            "Pizza" -> {
                pic = pizzaPic
                form = DataModel.Form.PIZZA
            }
            "Dryck" -> TODO()
            "Tillbehör" -> TODO()
        }
        return DataModel(networkDTO.name, networkDTO.topping.toString(), pic, networkDTO.price, form)
    }
    fun convertNetworkDTOListToDataModelList(networkDTOList: List<NetworkDTO>) : List<DataModel> {
        val list = mutableListOf<DataModel>()
        for (item in networkDTOList) {
            if (item.category == "Pizza") {
                convertNetworkDTOToDataModel(item).apply {
                    list.add(this)
                    list.add(this) // Выбрал неудачный API, в котором мало итемов, поэтому удвою, что есть
                }
            }
        }
        return list
    }
    fun convertDataModeToRoomDTO(dataModel: DataModel) : RoomDTO {
        return RoomDTO(
            dataModel.title,
            when (dataModel.form) {
                DataModel.Form.PIZZA -> "Pizza"
                DataModel.Form.COMBO -> TODO()
                DataModel.Form.WATER -> "Dryck"
                DataModel.Form.DESERT -> "Tillbehör"
            },
            dataModel.description,
            dataModel.price
        )
    }
    fun convertDataModelListToRoomDTOList(dataModelList: List<DataModel>) : List<RoomDTO> {
        return dataModelList.map {
            convertDataModeToRoomDTO(it)
        }
    }
    fun convertRoomDTOToDataMode(roomDTO: RoomDTO) : DataModel {
        return DataModel(
            roomDTO.name,
            roomDTO.description ?: "No description yet",
            when (roomDTO.category) {
                "Pizza" -> pizzaPic
                "Dryck" -> TODO()
                "Tillbehör" -> TODO()
                else -> TODO()
            },
            roomDTO.price,
            when (roomDTO.category) {
                "Pizza" -> DataModel.Form.PIZZA
                "Dryck" -> DataModel.Form.WATER
                "Tillbehör" -> DataModel.Form.DESERT
                else -> TODO()
            }
        )
    }
    fun convertRoomDTOListToDataModelList(roomDTOList: List<RoomDTO>) : List<DataModel> {
        return roomDTOList.map {
            convertRoomDTOToDataMode(it)
        }
    }
}