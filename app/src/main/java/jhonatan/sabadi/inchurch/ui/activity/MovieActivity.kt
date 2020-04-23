package jhonatan.sabadi.inchurch.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.repository.MovieRepository
import jhonatan.sabadi.inchurch.ui.adapter.MovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity(), OnRecyclerViewItemListener {

    private val movieViewModel by lazy {
        val movieRepository = MovieRepository()
        val factory by lazy { MovieViewModelFactory(movieRepository) }
        val provider = ViewModelProviders.of(this, factory)
        provider.get(MovieViewModel::class.java)
    }

    private val movieAdapter by lazy {
        MovieAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedElementEffect()
        setContentView(R.layout.activity_movie)

        initRecyclerView()
        initMovieList()

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

    private fun initMovieList() {
        movieViewModel.movies.observe(this, Observer {
            movieAdapter.submitList(it)
        })
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

    }
}