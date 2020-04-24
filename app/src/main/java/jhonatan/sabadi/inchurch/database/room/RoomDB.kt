package jhonatan.sabadi.inchurch.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.model.FavMovie


@Database(
    entities = arrayOf(
        FavMovie::class
    ),
    version = 7
)
abstract class RoomDB : RoomDatabase() {

    abstract fun favModieDao(): FavMovieDao

}
