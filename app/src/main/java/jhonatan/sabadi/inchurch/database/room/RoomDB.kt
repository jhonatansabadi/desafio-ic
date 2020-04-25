package jhonatan.sabadi.inchurch.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.database.dao.GenreDao
import jhonatan.sabadi.inchurch.model.Genre
import jhonatan.sabadi.inchurch.model.Movie


@Database(
    entities = arrayOf(
        Movie::class,
        Genre::class
    ),
    version = 3
)
abstract class RoomDB : RoomDatabase() {

    abstract fun favModieDao(): FavMovieDao
    abstract fun genreDao(): GenreDao

}
