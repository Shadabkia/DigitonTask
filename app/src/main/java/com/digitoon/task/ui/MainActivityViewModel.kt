package com.digitoon.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitoon.task.ui.MainActivityEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {

    private val mainActivityEventsChannel = Channel<MainActivityEvents>()
    val mainActivityEvents = mainActivityEventsChannel.receiveAsFlow()

    fun activityCreated() = viewModelScope.launch {
        mainActivityEventsChannel.send(MainActivityEvents.InitViews)
    }


}