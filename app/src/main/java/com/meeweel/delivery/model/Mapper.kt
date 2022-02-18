package com.meeweel.delivery.model

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
        return DataModel(networkDTO.name, networkDTO.topping.toString(), pizzaPic, networkDTO.price, form)
    }
    fun convertNetworkDTOListToDataModelList(networkDTOList: List<NetworkDTO>) : List<DataModel> {
        val list = mutableListOf<DataModel>()
        for (item in networkDTOList) {
            if (item.category == "Pizza") {
                list.add(convertNetworkDTOToDataModel(item))
                list.add(convertNetworkDTOToDataModel(item)) // Выбрал неудачный API, в котором мало итемов, поэтому удвою, что есть
            }
        }
        return list
    }
}