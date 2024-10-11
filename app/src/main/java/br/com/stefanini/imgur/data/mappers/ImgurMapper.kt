package br.com.stefanini.imgur.data.mappers

import br.com.stefanini.imgur.data.models.GalleryResponse
import br.com.stefanini.imgur.data.models.ImageResponse
import br.com.stefanini.imgur.domain.models.GalleryModel
import br.com.stefanini.imgur.domain.models.ImageModel

internal fun ImageResponse.mapToModel() = ImageModel(
    id = id.orEmpty(),
    type = type.orEmpty(),
    title = title.orEmpty(),
    imageUrl = imageUrl.orEmpty()
)

internal fun GalleryResponse.mapToModel() = GalleryModel(
    id = id.orEmpty(),
    title = title.orEmpty(),
    images = images?.map { it.mapToModel() } ?: emptyList()
)

internal fun List<GalleryResponse>.mapToModels() = map { it.mapToModel() }
