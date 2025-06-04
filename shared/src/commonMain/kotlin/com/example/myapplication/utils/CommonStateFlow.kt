package com.example.myapplication.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface CommonStateFlow<T> {
    fun subscribe(observer: (T) -> Unit)
    fun unsubscribe()
}

class CommonStateFlowImpl<T>(
    private val flow: StateFlow<T>
) : CommonStateFlow<T> {
    private var job: Job? = null

    override fun subscribe(observer: (T) -> Unit) {
        job = CoroutineScope(Dispatchers.Main).launch {
            flow.collect {
                observer(it)
            }
        }
    }

    override fun unsubscribe() {
        job?.cancel()
        job = null
    }
}
