package jhonatan.sabadi.inchurch.repository

import android.content.Context
import jhonatan.sabadi.inchurch.api.call.MovieApi
import jhonatan.sabadi.inchurch.api.retrofit.RetrofitService
import jhonatan.sabadi.inchurch.database.dao.GenreDao
import jhonatan.sabadi.inchurch.database.room.RoomDBSingleton
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.model.MovieResult
import retrofit2.await
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val genreDao: GenreDao
) {

    @Inject
    lateinit var favMovieRepository: FavMovieRepository

    private val retrofit by lazy {
        RetrofitService.createService(MovieApi::class.java)
    }


    suspend fun getMovies(page: Int): List<Movie> {
        try {
            loadGenres()
            val data = retrofit.getMovies(page = page).await()
            val movies = setFavMovie(data)
            return movies
        } catch (e: Exception) {
            e.printStackTrace()
            return listOf()
        }
    }


    private suspend fun setFavMovie(data: MovieResult): List<Movie> {
        val movies = data.results.map {
            val favMovie = favMovieRepository.getByMovieId(it.id)
            it.isFavorite = favMovie?.isFavorite ?: false
            it
        }
        return movies
    }

    private suspend fun loadGenres() {
        val genres = genreDao.getAll()
        if (genres.isEmpty()) {
            val data = retrofit.getGenres().await()
            genreDao.insertAll(data.genres)
        }
    }

    suspend fun getGneres(ids: List<Int>): List<String> {
        val genres = mutableListOf<String>()
        ids.forEach {
            val genre = genreDao.getById(it)
            genre?.let {
                genres.add(it.name)
            }
        }
        return genres
    }

}