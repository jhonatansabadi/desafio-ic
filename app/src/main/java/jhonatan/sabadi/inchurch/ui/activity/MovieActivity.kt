package jhonatan.sabadi.inchurch.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.ui.adapter.MovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import jhonatan.sabadi.inchurch.ui.viewmodel.resource.Resource

class MovieActivity : AppCompatActivity(), OnRecyclerViewItemListener {

    private val movieViewModel by lazy {
        val factory by lazy { MovieViewModelFactory(application) }
        val provider = ViewModelProviders.of(this, factory)
        provider.get(MovieViewModel::class.java)
    }

    private val movieAdapter by lazy {
        MovieAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        initRecyclerView()
        initMovieList()

    }

    private fun initRecyclerView() {

    }

    private fun initMovieList() {
        movieViewModel.movies.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d("MovieActivity", "initMovieList: ${it.data}")
                    //movieAdapter.submitList(it.data)
                }
                is Resource.Failure -> {

                }
            }
        })
    }

    override fun setOnRecyclerItemClick(view: View, position: Int) {
    }

    override fun setOnRecyclerItemLongClick(view: View, position: Int) {

    }

    override fun onFavIconClicked(view: View, position: Int, isChecked: Boolean) {

    }
}