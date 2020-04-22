package jhonatan.sabadi.inchurch.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource
import java.lang.Exception

class MovieViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val movieRepository by lazy { MovieRepository(application) }

    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies

    private val _movies = liveData {
        emit(Resource.Loading())
        try {
            val data = movieRepository.getMovies()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Failure())
        }
    }

}