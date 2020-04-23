package jhonatan.sabadi.inchurch.api.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository

class MovieDataSourceFactory(
    private val movieRepository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = MovieDataSource(movieRepository)

}