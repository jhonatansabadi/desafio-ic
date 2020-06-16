package jhonatan.sabadi.inchurch.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.database.dao.GenreDao
import jhonatan.sabadi.inchurch.database.room.RoomDB

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): RoomDB = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        "movie"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesFavMovieDao(roomDB: RoomDB): FavMovieDao = roomDB.favModieDao()

    @Provides
    fun providesGenreDao(roomDB: RoomDB): GenreDao = roomDB.genreDao()

}