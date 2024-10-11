package br.com.stefanini.imgur.domain.usecases

import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel

internal interface GetImageListUseCase {
    suspend fun execute(query: String): DataWrapper<List<GalleryModel>>
}