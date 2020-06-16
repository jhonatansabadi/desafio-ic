package jhonatan.sabadi.inchurch.api.datasource

import androidx.paging.DataSource
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val movieRepository: MovieRepository
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = MovieDataSource(movieRepository)

}