package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val adapterPosition = movieViewHolder.adapterPosition
        movieViewHolder.itemView.apply {
            setOnClickListener {
                onOnRecyclerViewItemListener.setOnRecyclerItemClick(it, adapterPosition)
            }
            setOnLongClickListener {
                onOnRecyclerViewItemListener.setOnRecyclerItemLongClick(it, adapterPosition)
                true
            }
        }
        return movieViewHolder
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
                movieImage.loadImageFromUrl(movie.posterPath)
            }
        }
    }
}