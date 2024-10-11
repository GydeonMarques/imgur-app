package br.com.stefanini.imgur.domain.models


sealed class DataWrapper<out T> {

    data class Success<out T>(
        val data: T,
    ) : DataWrapper<T>()

    data class Failure<T>(
        val code: Int,
        val message: String?,
        val errorType: String? = null,
        val errorCode: String? = null,
    ) : DataWrapper<T>()

    fun <M> map(mapper: (T) -> M): DataWrapper<M> = when (this) {
        is Success -> Success(mapper(data))
        is Failure -> Failure(code, message, errorType, errorCode)
    }

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Failure -> "Error[code=$code, message=$message, errorType=$errorType, errorCode=$errorCode]"
        }
    }
}
