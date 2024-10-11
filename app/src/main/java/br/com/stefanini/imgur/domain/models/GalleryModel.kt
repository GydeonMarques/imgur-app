package br.com.stefanini.imgur.domain.models

data class GalleryModel(
    val id: String,
    val title: String,
    val images: List<ImageModel>
)