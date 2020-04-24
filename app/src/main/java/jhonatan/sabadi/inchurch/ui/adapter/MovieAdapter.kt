package jhonatan.sabadi.inchurch.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.loadImageFromUrl
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.FavMovie
import jhonatan.sabadi.inchurch.model.Movie
import kotlinx.android.synthetic.main.recycler_movie.view.*

class MovieAdapter(
    private val onOnRecyclerViewItemListener: OnRecyclerViewItemListener
) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieViewHolder = MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie, parent, false),
            onOnRecyclerViewItemListener
        )
        val adapterPosition = movieViewHolder.layoutPosition + 1
        movieViewHolder.itemView.apply {
            setItemClick(adapterPosition)
        }
        return movieViewHolder
    }

    private fun View.setItemClick(adapterPosition: Int) {
        setOnClickListener {
            onOnRecyclerViewItemListener.setOnRecyclerItemClick(
                it,
                adapterPosition,
                getItem(adapterPosition)
            )
        }
        setOnLongClickListener {
            onOnRecyclerViewItemListener.setOnRecyclerItemLongClick(
                it,
                adapterPosition,
                getItem(adapterPosition)
            )
            true
        }
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(
        itemView: View,
        private val onOnRecyclerViewItemListener: OnRecyclerViewItemListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                movieTitle.text = movie.title
                setImage(movie)
                movieFav.apply {
                    changeFavState(movie)
                    setOnFavClicked(movie)
                }
            }
        }

        private fun CheckBox.setOnFavClicked(movie: Movie) {
            setOnClickListener {
                if (movie.favMovie == null) {
                    movie.favMovie = FavMovie(movieId = movie.id)
                }
                movie.favMovie?.let {
                    it.isChecked = !it.isChecked
                }
                changeFavState(movie)
                onOnRecyclerViewItemListener.onFavIconClicked(it, adapterPosition, isChecked, movie)
            }
        }

        private fun CheckBox.changeFavState(movie: Movie) {
            if (movie.favMovie == null) {
                isChecked = false
            } else {
                movie.favMovie?.let {
                    isChecked = it.isChecked
                }
            }
        }

        private fun View.setImage(movie: Movie) {
            movie.posterPath?.let {
                movieImage.loadImageFromUrl(it)
            }
        }
    }
}