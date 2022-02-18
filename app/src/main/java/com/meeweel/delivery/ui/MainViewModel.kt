package com.meeweel.delivery.ui

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.delivery.model.AppState
import com.meeweel.delivery.model.DataModel
import com.meeweel.delivery.repository.Repository
import com.meeweel.delivery.repository.RepositoryImpl
import kotlinx.coroutines.*

class MainViewModel(val repository: Repository = RepositoryImpl()) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

//    private val list: List<DataModel> = listOf(
//        DataModel("Pizza 1", "Super power description", "picture", 150, DataModel.Form.PIZZA),
//        DataModel("Pizza 2", "Super power description", "picture", 350, DataModel.Form.PIZZA),
//        DataModel("Pizza 3", "Super power description", "picture", 450, DataModel.Form.PIZZA),
//        DataModel("Pizza 4", "Super power description", "picture", 350, DataModel.Form.PIZZA),
//        DataModel("Pizza 5", "Super power description", "picture", 250, DataModel.Form.PIZZA),
//        DataModel("Pizza 6", "Super power description", "picture", 350, DataModel.Form.PIZZA),
//        DataModel("Pizza 7", "Super power description", "picture", 350, DataModel.Form.PIZZA)
//    )

    fun getLiveData() : LiveData<AppState> {
        return liveDataForViewToObserve
    }

//    fun findList() {
//        liveDataForViewToObserve.postValue(AppState.Success(list))
//    }

    fun getData(isOnline: Boolean) {

        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startDataLoading() }
    }

    private suspend fun startDataLoading() = withContext(Dispatchers.IO) {
        liveDataForViewToObserve.postValue(AppState.Success(repository.getData()))
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun handleError(throwable: Throwable) {
        TODO()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}