package jhonatan.sabadi.inchurch.repository

import android.content.Context
import jhonatan.sabadi.inchurch.database.room.RoomDBSingleton
import jhonatan.sabadi.inchurch.model.FavMovie

class FavMovieRepository(
    private val context: Context
) {

    private val favMovieDao by lazy {
        val db = RoomDBSingleton.getInstance(context)
        db.favModieDao()
    }

    suspend fun getAll(): List<FavMovie> = favMovieDao.getAll()

    suspend fun getByMovieId(movieId: Int): FavMovie = favMovieDao.getByMovieId(movieId)

    suspend fun insert(favMovie: FavMovie): Long = favMovieDao.insert(favMovie)

    suspend fun delete(movieId: Int): Unit = favMovieDao.delete(movieId)


}