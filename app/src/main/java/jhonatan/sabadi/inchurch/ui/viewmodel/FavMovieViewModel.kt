package jhonatan.sabadi.inchurch.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.FavMovieRepository

class FavMovieViewModel @ViewModelInject constructor(
    private val favMovieRepository: FavMovieRepository
) : ViewModel() {

    val favMovies get() = _favMovies

    private val _favMovies = liveData {
        val data = favMovieRepository.getAll()
        emitSource(data)
    }

    fun insert(favMovie: Movie) = liveData {
        val inserted = favMovieRepository.insert(favMovie)
        emit(inserted)
    }

    fun delete(movieId: Int) = liveData {
        val deleted = favMovieRepository.delete(movieId)
        emit(deleted)
    }

}