package jhonatan.sabadi.inchurch.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String) = Glide.with(context)
    .load("https://image.tmdb.org/t/p/w500${url}")
    .into(this)