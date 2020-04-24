package jhonatan.sabadi.inchurch.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovie(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    val movieId: Int,
    var isChecked: Boolean = false
)