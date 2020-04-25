package jhonatan.sabadi.inchurch.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class GenreResult(
    @SerializedName("genres")
    val genres: List<Genre>
)