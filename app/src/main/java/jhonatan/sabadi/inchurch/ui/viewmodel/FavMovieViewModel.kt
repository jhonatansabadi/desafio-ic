package jhonatan.sabadi.inchurch.ui.viewmodel

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jhonatan.sabadi.inchurch.model.FavMovie
import jhonatan.sabadi.inchurch.repository.FavMovieRepository

class FavMovieViewModel (
    private val context: Context
) : ViewModel() {

    val favMovieRepository  = FavMovieRepository(context)

    fun insert(favMovie: FavMovie) = liveData {
        val inserted = favMovieRepository.insert(favMovie)
        emit(inserted)
    }

    fun delete(movieId: Int) = liveData {
        val deleted = favMovieRepository.delete(movieId)
        emit(deleted)
    }

}