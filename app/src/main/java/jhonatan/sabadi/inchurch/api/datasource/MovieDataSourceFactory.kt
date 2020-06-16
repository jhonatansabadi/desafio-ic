package jhonatan.sabadi.inchurch.api.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val context: Context
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = MovieDataSource(context)

}