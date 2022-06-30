package com.paul.weatherassigment.data

sealed class MyResult<out R> {

    data class Success<out T>(val data: T) : MyResult<T>()
    data class Fail(val error: String) : MyResult<Nothing>()
    data class Error(val exception: Exception) : MyResult<Nothing>()
    object Loading : MyResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[result=$data]"
            is Fail -> "Fail[error=$error]"
            is Error -> "Error[exception=${exception.message}]"
            Loading -> "Loading"
        }
    }
}


val MyResult<*>.succeeded
    get() = this is MyResult.Success && data != null
