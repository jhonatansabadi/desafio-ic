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
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.interfaces.OnEmptyScreen
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import jhonatan.sabadi.inchurch.ui.adapter.FavMovieAdapter
import jhonatan.sabadi.inchurch.ui.viewmodel.FavMovieViewModel
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel

import jhonatan.sabadi.inchurch.ui.viewmodel.factory.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_fav_movie.*

@AndroidEntryPoint
class FavMovieActivity :
    AppCompatActivity(),
    OnRecyclerViewItemListener,
    SearchView.OnQueryTextListener,
    OnEmptyScreen {


    private val favMovieViewModel by viewModels<FavMovieViewModel>()
    private val favMovieAdapter by lazy {
        FavMovieAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initSharedElementEffect()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_movie)
        initToolbar()
        initRecyclerView()
        initMovieList()

    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun initSharedElementEffect() {
        val transformation: MaterialContainerTransform = MaterialContainerTransform().apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 500
        }
        window.sharedElementEnterTransition = transformation
    }

    private fun initRecyclerView() {
        recyclerViewFavMovie.apply {
            layoutManager = LinearLayoutManager(this@FavMovieActivity)
            hasFixedSize()
            adapter = favMovieAdapter
        }
    }

    private fun initMovieList() {
        favMovieViewModel.favMovies.observe(this, Observer {
            favMovieAdapter.submitList(it)
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

    override fun setOnRecyclerItemLongClick(view: View, position: Int, movie: Movie?) {}

    override fun onFavIconClicked(view: View, position: Int, isChecked: Boolean, movie: Movie?) {
        movie?.let {
            favMovieViewModel.delete(it.id).observe(this, Observer {
                favMovieAdapter.remove(position)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav_movie, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.menuSearch)
        val searchView = searchItem?.actionView as SearchView
        searchView.setQueryHint("Pesquise o Filme")
        searchView.setOnQueryTextListener(this)
        searchView.setIconified(false)

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                favMovieAdapter.removeFilter()
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId) {
            onBackPressed()
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        favMovieAdapter.filter(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onEmptyScreenListener(isEmpty: Boolean) {
        when {
            isEmpty -> favMovieEmptyImage.visibility = View.VISIBLE
            else -> favMovieEmptyImage.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MovieActivity::class.java)
        startActivity(intent)
    }


}