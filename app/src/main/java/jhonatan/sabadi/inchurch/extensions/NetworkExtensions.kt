package jhonatan.sabadi.inchurch.extensions

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}