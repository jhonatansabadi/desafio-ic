package jhonatan.sabadi.inchurch.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.loadImageFromUrl
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.FavMovieViewModelFactory
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_scrolling.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var movie: Movie
    private val REQUEST_CODE = 1
    private val movieViewModel by lazy {
        val factory by lazy { MovieViewModelFactory(application) }
        val provider = ViewModelProviders.of(this, factory)
        provider.get(MovieViewModel::class.java)
    }

    private val favMovieViewModel by lazy {
        val factory by lazy { FavMovieViewModelFactory(application) }
        val provider = ViewModelProviders.of(this, factory)
        provider.get(FavMovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        iniToolbar()
        movie = intent?.getSerializableExtra("movie") as Movie
        movie?.let {
            loadImage()
            loadGenres()
            loadTexts()
            loadReleaseDate()
            loadFav()
        }
    }

    private fun loadFav() {
        when (movie.isFavorite) {
            null -> fabDetails.setImageResource(R.drawable.ic_fav)
            else -> movie.isFavorite?.let {
                val icon = if (it) R.drawable.ic_fav_filled else R.drawable.ic_fav
                fabDetails.setImageResource(icon)
            }
        }
    }

    private fun loadReleaseDate() {
        detailsDate.text = movie.releaseDate
    }

    private fun loadTexts() {
        detailsTitle.text = movie.title
        detailsOverview.text = movie.overview
    }

    private fun loadGenres() {
        movieViewModel.getGenres(movie.genreIds).observe(this, Observer {
            val genresText = it.joinToString(separator = ", ")
            detailsGenres.text = genresText
        })
    }

    private fun iniToolbar() {
        anim_toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { onBackPressed() }
            title = ""
        }
        setSupportActionBar(anim_toolbar)
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        collapsing_toolbar.title = ""
    }

    private fun loadImage() {
        movie.backdropPath?.let {
            detailImage.loadImageFromUrl(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onFavClick(view: View) {
        movie.isFavorite?.let {
            when {
                it -> deleteFav(movie)
                else -> insertFav(movie)
            }
        }
    }

    private fun deleteFav(movie: Movie) {
        movie.isFavorite = false
        fabDetails.setImageResource(R.drawable.ic_fav)
        favMovieViewModel.delete(movie.id).observe(this, Observer {
            Toast.makeText(this, "Removido dos Favoritos", Toast.LENGTH_SHORT).show()
        })
    }

    private fun insertFav(movie: Movie) {
        movie.isFavorite = true
        fabDetails.setImageResource(R.drawable.ic_fav_filled)
        favMovieViewModel.insert(movie).observe(this, Observer {
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val position = intent.getIntExtra("position", 0)
        val intent = Intent().apply {
            putExtra("movie", movie)
            putExtra("position", position)
        }
        setResult(REQUEST_CODE, intent)
    }

}