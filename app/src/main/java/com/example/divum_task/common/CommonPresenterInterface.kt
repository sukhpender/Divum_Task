package com.example.divum_task.common

interface CommonPresenterInterface {
    fun onSuccess(response: Any?)
    fun apiFailed(title: String?)
}