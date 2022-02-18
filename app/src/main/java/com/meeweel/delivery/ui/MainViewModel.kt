package com.meeweel.delivery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.delivery.model.AppState
import com.meeweel.delivery.model.DataModel

class MainViewModel : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    private val list: List<DataModel> = listOf(
        DataModel("Pizza 1", "Super power description", "picture", 150, DataModel.Form.PIZZA),
        DataModel("Pizza 2", "Super power description", "picture", 350, DataModel.Form.PIZZA),
        DataModel("Pizza 3", "Super power description", "picture", 450, DataModel.Form.PIZZA),
        DataModel("Pizza 4", "Super power description", "picture", 350, DataModel.Form.PIZZA),
        DataModel("Pizza 5", "Super power description", "picture", 250, DataModel.Form.PIZZA),
        DataModel("Pizza 6", "Super power description", "picture", 350, DataModel.Form.PIZZA),
        DataModel("Pizza 7", "Super power description", "picture", 350, DataModel.Form.PIZZA)
    )

    fun getData() : LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun findList() {
        liveDataForViewToObserve.postValue(AppState.Success(list))
    }

}