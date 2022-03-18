package com.android.example.headspaceandroidcodingexercise.utils

sealed class UIState(val state: ResponseState) {
    class Success<T>(val data: T) : UIState(ResponseState.SUCCESS)
    class Error(val error: Throwable) : UIState(ResponseState.ERROR)
    class Loading(val isLoading: Boolean = true) : UIState(ResponseState.LOADING)
}

enum class ResponseState {
    LOADING,
    SUCCESS,
    ERROR;
}