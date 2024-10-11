package br.com.stefanini.imgur.domain.models

data class ImageModel(
    val id: String,
    val type: String,
    val title: String,
    val imageUrl: String
)