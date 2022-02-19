package com.meeweel.delivery.model

import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.model.entities.NetworkDTO
import com.meeweel.delivery.model.entities.RoomDTO

// Выбрал неудачный API, который не предоставляет картинки
val pizzaPic = arrayOf("https://static.tildacdn.com/tild3635-3633-4162-b035-633639383734/pizza-1.png",
"https://catherineasquithgallery.com/uploads/posts/2021-03/1614548194_3-p-pitstsa-na-belom-fone-5.jpg",
"https://trattoriauno.ru/assets/images/pizza/gornayanew.png")
val desertPic = arrayOf(
    "https://mykaleidoscope.ru/uploads/posts/2020-01/1579896921_37-p-deserti-s-shokoladom-90.jpg",
    "https://mykaleidoscope.ru/uploads/posts/2020-03/1583187672_31-p-tsvetnie-deserti-98.jpg",
    "https://mykaleidoscope.ru/uploads/posts/2020-01/1579909802_30-p-krasivie-italyanskie-deserti-69.jpg")
val waterPic = arrayOf("https://celestra.ru/uploads/photos/large/20.png",
"https://www.cocktail-db.com/stat/img/1280/RedCurrantCosmo.jpg",
"https://images.saymedia-content.com/.image/t_share/MTc5MTEzMDc5Mzk1NTkxMjEz/how-to-make-diet-shakes.jpg")

const val pizza = "Pizza"
const val dessert = "Tillbehör"
const val water = "Dryck"

class Mapper {
    private fun convertNetworkDTOToDataModel(networkDTO: NetworkDTO) : DataModel {
        var pic = pizzaPic[0]
        var form = DataModel.Form.PIZZA
        when (networkDTO.category) {
            pizza -> {
                pic = pizzaPic[((Math.random()*3).toInt())]
                form = DataModel.Form.PIZZA
            }
            water -> {
                pic = waterPic[((Math.random()*3).toInt())]
                form = DataModel.Form.WATER
            }
            dessert -> {

                pic = desertPic[((Math.random()*3).toInt())]
                form = DataModel.Form.DESSERT
            }
        }
        return DataModel(networkDTO.name, networkDTO.topping.toString(), pic, networkDTO.price, form)
    }
    fun convertNetworkDTOListToDataModelList(networkDTOList: List<NetworkDTO>) : List<DataModel> {
        val list = mutableListOf<DataModel>()
        for (item in networkDTOList) {
            convertNetworkDTOToDataModel(item).apply {
                list.add(this)
                list.add(this) // Выбрал неудачный API, в котором мало итемов, поэтому удвою, что есть
            }
        }
        return list
    }
    private fun convertDataModeToRoomDTO(dataModel: DataModel) : RoomDTO {
        return RoomDTO(
            dataModel.title,
            when (dataModel.form) {
                DataModel.Form.PIZZA -> pizza
                DataModel.Form.COMBO -> TODO()
                DataModel.Form.WATER -> water
                DataModel.Form.DESSERT -> dessert
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
    private fun convertRoomDTOToDataMode(roomDTO: RoomDTO) : DataModel {
        return DataModel(
            roomDTO.name,
            roomDTO.description ?: "No description yet",
            when (roomDTO.category) {
                pizza -> pizzaPic[((Math.random()*3).toInt())]
                water -> waterPic[((Math.random()*3).toInt())]
                dessert -> desertPic[((Math.random()*3).toInt())]
                else -> TODO()
            },
            roomDTO.price,
            when (roomDTO.category) {
                pizza -> DataModel.Form.PIZZA
                water -> DataModel.Form.WATER
                dessert -> DataModel.Form.DESSERT
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