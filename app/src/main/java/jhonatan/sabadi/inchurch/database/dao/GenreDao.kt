package jhonatan.sabadi.inchurch.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jhonatan.sabadi.inchurch.model.Genre
import jhonatan.sabadi.inchurch.model.Movie


@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    suspend fun getAll(): List<Genre>

    @Query("SELECT * FROM genre WHERE id == :id")
    suspend fun getById(id: Int): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: Genre): Long

    @Insert
    suspend fun insertAll(genres: List<Genre>)

    @Query("DELETE FROM genre WHERE id == :id")
    suspend fun delete(id: Int)

} 