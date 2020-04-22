package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie

class MovieAdapter(
    private val onOnRecyclerViewItemListener: OnRecyclerViewItemListener
) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    private val movies = listOf<Movie>()

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
        val movie = movies[position]
        holder.bind(movie)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {

        }

    }
}