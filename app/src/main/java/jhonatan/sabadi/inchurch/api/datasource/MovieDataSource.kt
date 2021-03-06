package jhonatan.sabadi.inchurch.api.datasource

import androidx.paging.PageKeyedDataSource
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

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