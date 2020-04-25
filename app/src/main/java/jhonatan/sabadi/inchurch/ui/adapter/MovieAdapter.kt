package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.loadImageFromUrl
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import kotlinx.android.synthetic.main.recycler_movie.view.*

class MovieAdapter(
    private val onOnRecyclerViewItemListener: OnRecyclerViewItemListener
) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie, parent, false),
            onOnRecyclerViewItemListener
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    fun changeFavState(position: Int, isFavorite: Boolean) {
        getItem(position)?.let {
            it.isFavorite = isFavorite
        }
        notifyItemChanged(position)
    }

    class MovieViewHolder(
        itemView: View,
        private val onOnRecyclerViewItemListener: OnRecyclerViewItemListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                movieTitle.text = movie.title
                setImage(movie)
                setItemClick(movie)
                movieFav.apply {
                    changeFavState(movie)
                    setOnFavClicked(movie)
                }
            }
        }

        private fun CheckBox.changeFavState(movie: Movie) {
            if (movie.isFavorite == null) {
                isChecked = false
            } else {
                movie.isFavorite?.let {
                    isChecked = it
                }
            }
        }

        private fun View.setItemClick(movie: Movie) {
            setOnClickListener {
                onOnRecyclerViewItemListener.setOnRecyclerItemClick(
                    it,
                    adapterPosition,
                    movie
                )
            }
            setOnLongClickListener {
                onOnRecyclerViewItemListener.setOnRecyclerItemLongClick(
                    it,
                    adapterPosition,
                    movie
                )
                true
            }
        }

        private fun CheckBox.setOnFavClicked(movie: Movie) {
            setOnClickListener {
                if (movie.isFavorite == null) {
                    movie.isFavorite = true
                    isChecked = true
                } else {
                    movie.isFavorite?.let {
                        if (it) {
                            isChecked = false
                            movie.isFavorite = false
                        } else {
                            isChecked = true
                            movie.isFavorite = true
                        }
                    }
                }
                onOnRecyclerViewItemListener.onFavIconClicked(it, adapterPosition, isChecked, movie)
            }
        }


        private fun View.setImage(movie: Movie) {
            movie.posterPath?.let {
                movieImage.loadImageFromUrl(it)
            }
        }

    }
}