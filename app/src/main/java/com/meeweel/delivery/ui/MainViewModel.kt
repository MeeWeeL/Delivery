package com.meeweel.delivery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.delivery.model.AppState
import com.meeweel.delivery.model.entities.DataModel
import com.meeweel.delivery.repository.Repository
import kotlinx.coroutines.*

class MainViewModel(
    private val repository: Repository<List<DataModel>>
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() : LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun getData(isOnline: Boolean, category: DataModel.Form) {

        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startDataLoading(isOnline, category) }
    }

    private suspend fun startDataLoading(isOnline: Boolean, category: DataModel.Form) = withContext(Dispatchers.IO) {
        repository.getData(isOnline, category).apply {
            liveDataForViewToObserve.postValue(AppState.Success(this))
            repository.insert(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun handleError(throwable: Throwable) {
        TODO("NEHOROSHO")
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}