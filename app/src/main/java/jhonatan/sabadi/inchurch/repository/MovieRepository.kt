package jhonatan.sabadi.inchurch.repository

import android.content.Context
import jhonatan.sabadi.inchurch.api.call.MovieApi
import jhonatan.sabadi.inchurch.api.retrofit.RetrofitService
import jhonatan.sabadi.inchurch.database.dao.FavMovieDao
import jhonatan.sabadi.inchurch.model.FavMovie
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.model.MovieResult
import retrofit2.await
import java.lang.Exception

class MovieRepository(
    private val context: Context
) {

    private val retrofit by lazy {
        RetrofitService.createService(MovieApi::class.java)
    }

    private val favMovieRepository by lazy {
        FavMovieRepository(context)
    }

    suspend fun getMovies(page: Int): List<Movie> {
        try {
            val data = retrofit.getMovies(page = page).await()
            val movies = getFavMovie(data)
            return movies
        } catch (e: Exception) {
            e.printStackTrace()
            return listOf()
        }
    }

    private suspend fun getFavMovie(data: MovieResult): List<Movie> {
        val movies = data.results.map {
            it.favMovie = favMovieRepository.getByMovieId(it.id)
            it
        }
        return movies
    }

}