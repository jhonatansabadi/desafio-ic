package jhonatan.sabadi.inchurch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import jhonatan.sabadi.inchurch.R
import jhonatan.sabadi.inchurch.model.Movie


class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}