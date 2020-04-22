package jhonatan.sabadi.inchurch.api.call

import jhonatan.sabadi.inchurch.model.Genre
import jhonatan.sabadi.inchurch.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String? = "639fef3ed98e333f7d0da8310b6ec26a",
        @Query("language") language: String? = "pt-BR",
        @Query("page") page: Int
    ): Call<List<Movie>>

    @GET("/3/genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String? = "639fef3ed98e333f7d0da8310b6ec26a",
        @Query("language") language: String? = "pt-BR",
    ): Call<List<Genre>>

}