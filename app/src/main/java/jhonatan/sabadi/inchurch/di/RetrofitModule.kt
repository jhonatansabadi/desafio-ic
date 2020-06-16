package jhonatan.sabadi.inchurch.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jhonatan.sabadi.inchurch.api.call.MovieApi
import jhonatan.sabadi.inchurch.api.retrofit.RetrofitService
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitModule {


    @Singleton
    @Provides
    fun providesMoviesApi(): MovieApi = RetrofitService.createService(MovieApi::class.java)

}