package br.com.stefanini.imgur.ui.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel
import br.com.stefanini.imgur.domain.usecases.GetImageListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<ImageListViewState>()
    val data: LiveData<ImageListViewState> get() = _data

    private val validImageTypes = setOf("image/png", "image/jpg", "image/jpeg")
    private val query = "cats"

    init {
        getImageList()
    }

    private fun getImageList() {
        viewModelScope.launch {
            emitState(ImageListViewState.Loading)
            when (val response = getImageListUseCase.execute(query)) {
                is DataWrapper.Failure -> emitState(ImageListViewState.Failure)
                is DataWrapper.Success -> emitState(
                    ImageListViewState.Success(
                        galleries = filterImagesOnly(response.data)
                    )
                )
            }
        }
    }

    private suspend fun filterImagesOnly(galleries: List<GalleryModel>): List<GalleryModel> {
        return withContext(Dispatchers.IO) {
            galleries.map { gallery ->
                gallery.copy(
                    images = gallery.images.filter { image -> image.type in validImageTypes }
                )
            }
        }
    }

    private fun emitState(state: ImageListViewState) {
        _data.value = state
    }
}