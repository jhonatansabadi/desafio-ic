package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.extensions.loadImageFromUrl
import jhonatan.sabadi.inchurch.interfaces.OnEmptyScreen
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie
import kotlinx.android.synthetic.main.recycler_fav_movie.view.*
import javax.inject.Inject

class FavMovieAdapter @Inject constructor(
    private val onRecyclerViewItemListener: OnRecyclerViewItemListener,
    private val onEmptyScreen: OnEmptyScreen
) : RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    private val favMovies = mutableListOf<Movie>()
    private val oldFavMovies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val favMovieViewHolder = FavMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_fav_movie, parent, false
            ),
            onRecyclerViewItemListener
        )
        val adapterPosition = favMovieViewHolder.adapterPosition + 1
        favMovieViewHolder.itemView.setItemClick(adapterPosition)
        return favMovieViewHolder
    }


    private fun View.setItemClick(adapterPosition: Int) {
        setOnClickListener {
            onRecyclerViewItemListener.setOnRecyclerItemClick(
                it,
                adapterPosition,
                favMovies[adapterPosition]
            )
        }
        setOnLongClickListener {
            onRecyclerViewItemListener.setOnRecyclerItemLongClick(
                it,
                adapterPosition,
                favMovies[adapterPosition]
            )
            true
        }
    }

    fun submitList(newFavMovies: List<Movie>) {
        favMovies.clear()
        favMovies.addAll(newFavMovies)
        notifyDataSetChanged()
        emptyScreen()
    }

    fun remove(position: Int) {
        favMovies.removeAt(position)
        notifyItemRemoved(position)
        emptyScreen()
    }

    private fun emptyScreen() {
        onEmptyScreen.onEmptyScreenListener(favMovies.isEmpty())
    }

    override fun getItemCount(): Int = favMovies.size

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = favMovies[position]
        holder.bind(movie)
    }

    fun filter(query: String?) {
        oldFavMovies.addAll(favMovies)
        val filteredMovies = mutableListOf<Movie>()
        query?.let { query ->
            oldFavMovies.forEach {
                if (it.title.toLowerCase().contains(query.toLowerCase())) {
                    filteredMovies.add(it)
                }
            }
        }
        submitList(filteredMovies)
    }

    fun removeFilter() {
        submitList(oldFavMovies)
    }

    class FavMovieViewHolder(
        itemView: View,
        private val onRecyclerViewItemListener: OnRecyclerViewItemListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                favMovieTitle.text = movie.title
                favMovieDesc.text = movie.overview
                favMovieDate.text = movie.releaseDate
                movie.posterPath?.let {
                    favMovieImage.loadImageFromUrl(it)
                }
                favMovieCheckBox.setOnClickListener {
                    onRecyclerViewItemListener.onFavIconClicked(
                        it,
                        adapterPosition,
                        true,
                        movie
                    )
                }
            }
        }
    }

}
