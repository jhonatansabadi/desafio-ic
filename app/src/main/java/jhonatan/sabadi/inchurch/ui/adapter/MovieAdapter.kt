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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieViewHolder = MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie, parent, false)
        )
        val adapterPosition = movieViewHolder.adapterPosition + 1
        movieViewHolder.itemView.apply {
            setItemClick(adapterPosition)
            movieFav.setFavClick(adapterPosition)
        }
        return movieViewHolder
    }

    private fun View.setItemClick(adapterPosition: Int) {
        setOnClickListener {
            onOnRecyclerViewItemListener.setOnRecyclerItemClick(it, adapterPosition, getItem(adapterPosition))
        }
        setOnLongClickListener {
            onOnRecyclerViewItemListener.setOnRecyclerItemLongClick(it, adapterPosition, getItem(adapterPosition))
            true
        }
    }

    private fun CheckBox.setFavClick(adapterPosition: Int) {
         setOnCheckedChangeListener { buttonView, isChecked ->
            onOnRecyclerViewItemListener.onFavIconClicked(buttonView, adapterPosition, isChecked, getItem(adapterPosition))
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                movieTitle.text = movie.title
                movie.posterPath?.let {
                    movieImage.loadImageFromUrl(it)
                }
            }
        }
    }
}