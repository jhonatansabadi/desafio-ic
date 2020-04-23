package jhonatan.sabadi.inchurch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.api.datasource.MovieDataSourceFactory
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource

class MovieViewModel(movieRepository: MovieRepository) : ViewModel() {

    private val movieDataSourceFactory = MovieDataSourceFactory(movieRepository)

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

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .setPrefetchDistance(5)
        .build()

}