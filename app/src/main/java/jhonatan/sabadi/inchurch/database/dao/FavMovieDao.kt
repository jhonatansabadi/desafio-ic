package jhonatan.sabadi.inchurch.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jhonatan.sabadi.inchurch.model.Movie


@Dao
interface FavMovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id == :movieId")
    suspend fun getByMovieId(movieId: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favMovie: Movie): Long

    @Query("DELETE FROM movie WHERE id == :movieId")
    suspend fun delete(movieId: Int)

} 