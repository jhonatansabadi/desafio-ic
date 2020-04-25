package jhonatan.sabadi.inchurch.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.api.datasource.MovieDataSourceFactory
import jhonatan.sabadi.inchurch.model.Genre
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource

class MovieViewModel(
    private val context: Context
) : ViewModel() {

    private val movieDataSourceFactory by lazy { MovieDataSourceFactory(context) }
    private val movieRepository by lazy { MovieRepository(context) }

    val movies get() = _movies

    private val _movies: LiveData<PagedList<Movie>> = liveData {
        try {
            val data = LivePagedListBuilder(
                movieDataSourceFactory,
                pagedListConfig()
            ).build()
            emitSource(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getGenres(ids: List<Int>): LiveData<List<String>> = liveData {
        val data = movieRepository.getGneres(ids)
        emit(data)
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(5)
        .build()

}