package jhonatan.sabadi.inchurch.repository

import android.content.Context
import androidx.paging.PagedList
import jhonatan.sabadi.inchurch.api.call.MovieApi
import jhonatan.sabadi.inchurch.api.retrofit.RetrofitService
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.model.Result
import retrofit2.await

class MovieRepository{

    private val retrofit by lazy {
        RetrofitService.createService(MovieApi::class.java)
    }


    suspend fun getMovies(page: Int): List<Movie> {
        val data = retrofit.getMovies(page = page).await()
        return data.results
    }
}