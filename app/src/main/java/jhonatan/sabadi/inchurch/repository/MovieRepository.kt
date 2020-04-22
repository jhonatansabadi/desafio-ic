package jhonatan.sabadi.inchurch.repository

import android.content.Context
import jhonatan.sabadi.inchurch.api.call.MovieApi
import jhonatan.sabadi.inchurch.api.retrofit.RetrofitService
import jhonatan.sabadi.inchurch.model.Movie
import retrofit2.await

class MovieRepository(
    private val context: Context
) {

    private val retrofit by lazy {
        RetrofitService.createService(MovieApi::class.java)
    }

    suspend fun getMovies(): List<Movie> {
        val data = retrofit.getMovies(page = 1).await()
        return data
    }
}