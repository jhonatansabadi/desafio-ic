package jhonatan.sabadi.inchurch.interfaces

import android.view.View
import jhonatan.sabadi.inchurch.model.Movie

interface OnRecyclerViewItemListener {

    fun setOnRecyclerItemClick(view: View, position: Int, movie: Movie?)

    fun setOnRecyclerItemLongClick(view: View, position: Int, movie: Movie?)

    fun onFavIconClicked(view: View, position: Int, isChecked: Boolean, movie: Movie?)

}