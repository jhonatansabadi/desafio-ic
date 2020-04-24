package jhonatan.sabadi.inchurch.api.datasource

import android.content.Context
import androidx.paging.PageKeyedDataSource
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDataSource(
    private val context: Context
) : PageKeyedDataSource<Int, Movie>() {

    private val movieRepository = MovieRepository(context)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = movieRepository.getMovies(1)
            callback.onResult(data, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            val page = params.key + 1
            val data = movieRepository.getMovies(page)
            callback.onResult(data, page)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}