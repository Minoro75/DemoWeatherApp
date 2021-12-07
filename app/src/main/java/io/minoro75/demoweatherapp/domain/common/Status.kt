package io.minoro75.demoweatherapp.domain.common

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}
