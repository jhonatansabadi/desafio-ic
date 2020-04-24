package jhonatan.sabadi.inchurch.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jhonatan.sabadi.inchurch.model.FavMovie

@Dao
interface FavMovieDao {

    @Query("SELECT * FROM favmovie")
    suspend fun getAll(): List<FavMovie>

    @Query("SELECT * FROM favmovie WHERE movieId == :movieId")
    suspend fun getByMovieId(movieId: Int): FavMovie

    @Insert
    suspend fun insert(favMovie: FavMovie): Long

    @Query("DELETE FROM FavMovie WHERE movieId == :movieId")
    suspend fun delete(movieId: Int)

} 