package jhonatan.sabadi.inchurch.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import jhonatan.sabadi.inchurch.model.Movie

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
                && oldItem.id == newItem.id
                && oldItem.id == newItem.id
    }

}