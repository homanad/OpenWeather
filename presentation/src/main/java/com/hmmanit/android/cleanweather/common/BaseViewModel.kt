package com.hmmanit.android.cleanweather.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventMessage = MutableLiveData<Event<String>>()

    val disposables = CompositeDisposable()

    fun showMessage(message: String) {
        eventMessage.value = Event(message)
    }

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}