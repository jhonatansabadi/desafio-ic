package jhonatan.sabadi.inchurch.ui.viewmodel.resource

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val throwable: Throwable? = null) : Resource<T>()
}