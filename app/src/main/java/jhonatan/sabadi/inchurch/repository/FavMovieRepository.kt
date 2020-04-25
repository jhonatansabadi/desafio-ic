package jhonatan.sabadi.inchurch.repository

import android.content.Context
import androidx.paging.DataSource
import jhonatan.sabadi.inchurch.database.room.RoomDBSingleton
import jhonatan.sabadi.inchurch.model.Movie

class FavMovieRepository(
    private val context: Context
) {

    private val favMovieDao by lazy {
        val db = RoomDBSingleton.getInstance(context)
        db.favModieDao()
    }

    suspend fun getAll(): List<Movie> = favMovieDao.getAll()

    suspend fun getByMovieId(movieId: Int): Movie = favMovieDao.getByMovieId(movieId)

    suspend fun insert(favMovie: Movie): Long = favMovieDao.insert(favMovie)

    suspend fun delete(movieId: Int): Unit = favMovieDao.delete(movieId)


}