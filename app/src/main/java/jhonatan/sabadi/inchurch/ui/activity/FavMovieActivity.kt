package jhonatan.sabadi.inchurch.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.isNetworkAvailable
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.ui.adapter.FavMovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.FavMovieViewModelFactory
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_fav_movie.*

class FavMovieActivity : AppCompatActivity(), OnRecyclerViewItemListener {

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

    private val favMovieAdapter by lazy {
        FavMovieAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_movie)

        initRecyclerView()
        checkInternetAndInitMovieList()

    }

    private fun initRecyclerView() {
        recyclerViewFavMovie.apply {
            layoutManager = LinearLayoutManager(this@FavMovieActivity)
            hasFixedSize()
            adapter = favMovieAdapter
        }
    }

    private fun checkInternetAndInitMovieList() {
        when {
            isNetworkAvailable() -> {
                //hideEmptyBackground()
                initMovieList()
            }
            else -> {
//                showEmptyBackground()
//                hideLoading()
            }
        }
    }

    private fun initMovieList() {
        favMovieViewModel.favMovies.observe(this, Observer {
            favMovieAdapter.submitList(it)
            //hideLoading()
        })
    }

    override fun setOnRecyclerItemClick(view: View, position: Int, movie: Movie?) {
        TODO("Not yet implemented")
    }

    override fun setOnRecyclerItemLongClick(view: View, position: Int, movie: Movie?) {
        TODO("Not yet implemented")
    }

    override fun onFavIconClicked(view: View, position: Int, isChecked: Boolean, movie: Movie?) {
        TODO("Not yet implemented")
    }
}