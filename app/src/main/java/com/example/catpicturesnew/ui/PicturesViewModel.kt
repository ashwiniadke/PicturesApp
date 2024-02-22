package com.example.catpicturesnew.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catpicturesnew.PicumApplication
import com.example.catpicturesnew.data.PicsumNetworkRepository
import com.example.catpicturesnew.data.Picture
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface CatUiState {
    data class Success(val photos: List<Picture>) : CatUiState
    object Error: CatUiState
    object Loading: CatUiState
}
class CatPictureViewModel(
    private val repository: PicsumNetworkRepository
): ViewModel() {
    var catUiState: CatUiState by mutableStateOf(CatUiState.Loading)
        private set

    init {
        getPictures()
    }

    fun getPictures() {
        viewModelScope.launch {
            catUiState = CatUiState.Loading
            catUiState = try {
                CatUiState.Success(repository.getPictures(page = 1, perPage = 30))
            } catch (e: IOException) {
                CatUiState.Error
            } catch (e: HttpException) {
                CatUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PicumApplication)
                val repo = application.container.repository
                CatPictureViewModel(repository = repo)
            }
        }
    }
}