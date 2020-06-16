package jhonatan.sabadi.inchurch.ui.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jhonatan.sabadi.inchurch.repository.FavMovieRepository
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel

//class FavMovieViewModelFactory(
//    private val favMovieRepository: FavMovieRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return FavMovieViewModel(favMovieRepository) as T
//    }
//}