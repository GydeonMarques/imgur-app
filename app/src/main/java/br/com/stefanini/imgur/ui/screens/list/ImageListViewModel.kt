package br.com.stefanini.imgur.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel
import br.com.stefanini.imgur.domain.usecases.GetImageListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
) : ViewModel() {

    private val _data = MutableStateFlow<ImageListViewState>(ImageListViewState.Loading)
    val data: StateFlow<ImageListViewState> get() = _data.asStateFlow()

    private val validImageTypes = setOf("image/png", "image/jpg", "image/jpeg")

    //For demonstration purposes, this value will be fixed here
    private val query = "cats"

    init {
        getImageList()
    }

    private fun getImageList() {
        viewModelScope.launch {
            try {
                emitState(ImageListViewState.Loading)
                when (val response = getImageListUseCase.execute(query)) {
                    is DataWrapper.Failure -> emitState(ImageListViewState.Failure)
                    is DataWrapper.Success -> emitState(
                        ImageListViewState.Success(
                            galleries = filterImagesOnly(response.data)
                        )
                    )
                }
            } catch (e: Exception) {
                emitState(ImageListViewState.Failure)
            }
        }
    }

    private fun filterImagesOnly(galleries: List<GalleryModel>): List<GalleryModel> {
        return galleries.map { gallery ->
            gallery.copy(
                images = gallery.images.filter { image -> image.type in validImageTypes }
            )
        }
    }

    private fun emitState(state: ImageListViewState) {
        _data.update { state }
    }
}