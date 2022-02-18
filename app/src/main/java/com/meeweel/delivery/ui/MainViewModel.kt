package com.meeweel.delivery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.delivery.model.AppState
import com.meeweel.delivery.repository.Repository
import com.meeweel.delivery.repository.RepositoryImpl
import kotlinx.coroutines.*
import okhttp3.internal.wait

class MainViewModel(
    private val repository: Repository = RepositoryImpl()
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

    fun getData(isOnline: Boolean) {

        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startDataLoading(isOnline) }
    }

    private suspend fun startDataLoading(isOnline: Boolean) = withContext(Dispatchers.IO) {
        repository.getData(isOnline).apply {
            liveDataForViewToObserve.postValue(AppState.Success(this))
            repository.insert(this)
        }
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