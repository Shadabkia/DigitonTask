package com.digitoon.task.data.network

data class ModelState<T>(
    var isLoading: Boolean = false,
    var response: T? = null,
    var error: String = ""
)