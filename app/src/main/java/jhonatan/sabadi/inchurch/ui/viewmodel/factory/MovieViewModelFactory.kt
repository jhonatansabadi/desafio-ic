package jhonatan.sabadi.inchurch.ui.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jhonatan.sabadi.inchurch.ui.viewmodel.MovieViewModel

class MovieViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(application) as T
    }
}