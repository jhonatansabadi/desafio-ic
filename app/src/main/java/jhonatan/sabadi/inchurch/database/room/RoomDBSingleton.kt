package jhonatan.sabadi.inchurch.database.room

import android.content.Context
import androidx.room.Room

object RoomDBSingleton {
    fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            RoomDB::class.java,
            "movie"
        )
        .fallbackToDestructiveMigration()
        .build()
}