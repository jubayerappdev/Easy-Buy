package com.creativeitinstitute.easybuy.core

sealed class DataState <T>(
    var message:String? = null,
    var data:T? = null

){
    class Loading<T> : DataState<T>()
    class Success<T> (jdata:T?) : DataState<T>(data = jdata)
    class Error<T>(message: String?) : DataState<T>(message)
}