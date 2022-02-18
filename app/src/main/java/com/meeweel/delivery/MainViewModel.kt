package com.meeweel.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<List<String>> = MutableLiveData()
    private val list: List<String> = listOf("Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4", "Pizza 5")
    fun getData() : LiveData<List<String>> {
        return liveDataForViewToObserve
    }

    fun findList() {
        liveDataForViewToObserve.postValue(list)
    }

}