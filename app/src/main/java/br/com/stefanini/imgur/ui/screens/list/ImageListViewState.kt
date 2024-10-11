package br.com.stefanini.imgur.ui.screens.list

import br.com.stefanini.imgur.domain.models.GalleryModel

internal sealed class ImageListViewState {
    data object Loading : ImageListViewState()
    data object Failure : ImageListViewState()
    data class Success(val galleries: List<GalleryModel>) : ImageListViewState()
}