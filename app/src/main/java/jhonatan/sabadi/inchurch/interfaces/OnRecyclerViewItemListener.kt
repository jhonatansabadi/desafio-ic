package jhonatan.sabadi.inchurch.interfaces

import android.view.View

interface OnRecyclerViewItemListener {

    fun setOnRecyclerItemClick(view: View, position: Int)

    fun setOnRecyclerItemLongClick(view: View, position: Int)

    fun onFavIconClicked(view: View, position: Int, isChecked: Boolean)

}