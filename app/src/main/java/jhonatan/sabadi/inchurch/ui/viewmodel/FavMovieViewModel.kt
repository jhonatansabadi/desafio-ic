package jhonatan.sabadi.inchurch.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.FavMovieRepository

class FavMovieViewModel (
    private val context: Context
) : ViewModel() {

    val favMovieRepository  = FavMovieRepository(context)

    val favMovies get() = _favMovies

    private val _favMovies = liveData {
        val data = favMovieRepository.getAll()
        emit(data)
    }

    fun insert(favMovie: Movie) = liveData {
        val inserted = favMovieRepository.insert(favMovie)
        emit(inserted)
    }

    fun delete(movieId: Int) = liveData {
        val deleted = favMovieRepository.delete(movieId)
        emit(deleted)
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(5)
        .build()

}