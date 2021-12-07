package io.minoro75.demoweatherapp.utils

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}
