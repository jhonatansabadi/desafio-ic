package jhonatan.sabadi.inchurch.api.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val BASE_URL = "https://api.themoviedb.org"

    fun <T> createService(serviceClass: Class<T>?): T {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(serviceClass)
    }
}