package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.interfaces.OnRecyclerViewItemListener
import jhonatan.sabadi.inchurch.model.Movie

class FavMovieAdapter(
    private val onRecyclerViewItemListener: OnRecyclerViewItemListener
) : RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    private val favMovies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val favMovieViewHolder = FavMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_fav_movie, parent, false
            )
        )
        favMovieViewHolder.itemView.setItemClick(favMovieViewHolder.adapterPosition)
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
    }

    override fun getItemCount(): Int = favMovies.size

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {

    }

    class FavMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}
