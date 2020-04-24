package jhonatan.sabadi.inchurch.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.database.room.RoomDBSingleton
import jhonatan.sabadi.inchurch.extensions.isNetworkAvailable
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.FavMovie
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.adapter.MovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.FavMovieViewModelFactory
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity :
    AppCompatActivity(),
    OnRecyclerViewItemListener {

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

    private val movieAdapter by lazy {
        MovieAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedElementEffect()
        setContentView(R.layout.activity_movie)

        showLoading()
        initRecyclerView()
        checkInternetAndInitMovieList()

    }

    private fun initSharedElementEffect() {
        val transformation: MaterialContainerTransform = MaterialContainerTransform().apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 500
        }
        window.sharedElementEnterTransition = transformation
    }

    private fun initRecyclerView() {
        recyclerViewMovie.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            hasFixedSize()
            adapter = movieAdapter
        }
    }

    private fun checkInternetAndInitMovieList() {
        when {
            isNetworkAvailable() -> {
                hideEmptyBackground()
                initMovieList()
            }
            else -> {
                showEmptyBackground()
                hideLoading()
            }
        }
    }

    private fun initMovieList() {
        movieViewModel.movies.observe(this, Observer {
            movieAdapter.submitList(it)
            hideLoading()
        })
    }

    private fun showEmptyBackground() {
        noInternetMovie.visibility = View.VISIBLE
    }

    private fun hideEmptyBackground() {
        noInternetMovie.visibility = View.GONE
    }

    private fun showLoading() {
        progressLoadingMovie.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressLoadingMovie.visibility = View.GONE
    }


    override fun setOnRecyclerItemClick(view: View, position: Int, movie: Movie?) {
        movie?.let {
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("movie", it)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                view,
                "movie"
            )
            startActivity(intent, options.toBundle())
        }
    }

    override fun setOnRecyclerItemLongClick(view: View, position: Int, movie: Movie?) {

    }

    override fun onFavIconClicked(view: View, position: Int, isChecked: Boolean, movie: Movie?) {
        movie?.let {
            if (isChecked) {
                val favMovie = FavMovie(
                    movieId = it.id,
                    isChecked = isChecked
                )
                favMovieViewModel.insert(favMovie).observe(this, Observer {
                    Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuFavMovie -> openFavMovies()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFavMovies() {
        val intent = Intent(this, FavMovieActivity::class.java)
        startActivity(intent)
    }

}









