package jhonatan.sabadi.inchurch.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.laodImageFromUrl(url: String) = Glide.with(context)
    .load(url)
    .into(this)