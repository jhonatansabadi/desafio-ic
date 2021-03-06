package jhonatan.sabadi.inchurch.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.isNetworkAvailable
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.ui.adapter.MovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factFavMovieViewModelFactory
import kotlinx.android.synthetic.main.activity_movie.*

@AndroidEntryPoint
class MovieActivity :
    AppCompatActivity(),
    OnRecyclerViewItemListener {

    private val REQUEST_CODE: Int = 0

    private val favMovieViewModel by viewModels<FavMovieViewModel>()
    private val movieViewModel by viewModels<MovieViewModel>()

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
                putExtra("position", position)
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                view,
                "movie"
            )
            startActivityForResult(intent, REQUEST_CODE, options.toBundle())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val movie = data?.getSerializableExtra("movie") as Movie
            val position = data.getIntExtra("position", 0)
            movie?.let {
                movieAdapter.changeFavState(position, movie.isFavorite ?: false)
            }
        }
    }

    override fun setOnRecyclerItemLongClick(view: View, position: Int, movie: Movie?) {

    }

    override fun onFavIconClicked(view: View, position: Int, isChecked: Boolean, movie: Movie?) {
        movie?.let {
            when {
                isChecked -> insertFav(it, isChecked)
                else -> deleteFav(it)
            }
        }
    }

    private fun deleteFav(movie: Movie) {
        favMovieViewModel.delete(movie.id).observe(this, Observer {
            Toast.makeText(this, "Removido dos Favoritos", Toast.LENGTH_SHORT).show()
        })
    }

    private fun insertFav(movie: Movie, isChecked: Boolean) {
        movie.isFavorite = isChecked
        favMovieViewModel.insert(movie).observe(this, Observer {
            Toast.makeText(this, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
        })
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
        finish()
    }

}









