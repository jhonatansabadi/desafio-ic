package jhonatan.sabadi.inchurch.repository

import androidx.lifecycle.LiveData
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.model.Movie
import javax.inject.Inject

class FavMovieRepository @Inject constructor(
    private val favMovieDao: FavMovieDao
) {

    fun getAll(): LiveData<List<Movie>> = favMovieDao.getAll()

    suspend fun getByMovieId(movieId: Int): Movie = favMovieDao.getByMovieId(movieId)

    suspend fun insert(favMovie: Movie): Long = favMovieDao.insert(favMovie)

    suspend fun delete(movieId: Int): Unit = favMovieDao.delete(movieId)


}