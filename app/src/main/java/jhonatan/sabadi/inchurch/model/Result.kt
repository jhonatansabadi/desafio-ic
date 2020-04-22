package jhonatan.sabadi.inchurch.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<Movie>
) : Serializable