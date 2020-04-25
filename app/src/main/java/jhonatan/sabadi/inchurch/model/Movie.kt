package jhonatan.sabadi.inchurch.model


import android.R
import androidx.room.*
import com.google.gson.annotations.SerializedName
import jhonatan.sabadi.inchurch.database.converters.ListConverter
import java.io.Serializable

@Entity
@TypeConverters(ListConverter::class)
data class Movie(

    @SerializedName("id")
    @PrimaryKey
    val id: Int,

    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,

    var isFavorite: Boolean? = null

) : Serializable